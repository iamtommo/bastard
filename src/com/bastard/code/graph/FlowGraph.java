package com.bastard.code.graph;

import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.code.graph.block.CodeBlock;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;

/**
 * A representation of the program's control flow. 
 * When complete, this graph will represent all possible entry/exit points
 * of the local method code.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class FlowGraph extends Graph<CodeBlock> {

	private LabelInstruction[] labels;

	public FlowGraph(CodeAttribute code) {
		super(code);
		this.labels = new LabelInstruction[code.getInstructionList().size() + 1];
	}

	@Override
	public void construct() {
		createLabels();
		createBaseBlocks();
	}

	/**
	 * Creates basic code blocks using the labels we previously defined.
	 * 
	 * One code block is represented as all instructions between startLabel.pc + 1
	 * and endLabel.pc.
	 * 
	 * Once one block has been created, endLabel.pc becomes startLabel.pc and 
	 * the next available label becomes the new endLabel, until the endLabel 
	 * is the sink.
	 */
	public void createBaseBlocks() {
		CodeBlock source = new CodeBlock(labels[0]);
		source.setTag("SOURCE");

		addVertex(source);

		CodeBlock sink = new CodeBlock(getSink());
		sink.setTag("SINK");

		LabelInstruction start = next(source.getStart());
		LabelInstruction end;
		while((end = next(start)) != null && end != sink.getStart()) {
			if ((boolean) start.getAttribute("root")) {
				CodeBlock block = new CodeBlock(start);
				addVertex(block);
			}
			start = end;
		}

		addVertex(sink); // Add the sink block at the end, rather than when it was created
		// in an attempt to maintain some order.

		addEdge(source, sink);
		addEdge(source, getVertex(1));

		int index = 0;
		for (CodeBlock block : vertices) {
			System.out.println(block);
			
			InstructionList sliced = slice(code.getInstructionList(), index);
			
			index += sliced.size();
			
			block.setInstructions(sliced);
		}
	}

	public InstructionList slice(InstructionList instructions, int start) {
		InstructionList list = new InstructionList();
		for (int i = start; i < instructions.size(); i++) {
			Instruction insn = instructions.get(i);
			
			list.add(insn);
			if (insn instanceof JumpInstruction) {
				return list;
			}
		}
		return list;
	}

	/**
	 * Creates the basic labels for this method (root and sink), and labels
	 * for every jump in between.
	 * 
	 * Some minor jump optimization takes place here.
	 */
	public void createLabels() {
		LabelInstruction root = labels[0] = new LabelInstruction(pool, 0);
		root.setAttribute("root", true);

		InstructionList instructions = code.getInstructionList();
		for (int i = 0; i < instructions.size(); i++) {
			Instruction insn = instructions.get(i);

			if (insn instanceof JumpInstruction) {
				JumpInstruction jump = (JumpInstruction) insn;

				if (jump.getDestination() < instructions.size()) {

					Instruction target = instructions.get(jump.getDestination());

					if (target instanceof JumpInstruction) { // branch Jump->Jump->Target to Jump->Target
						jump.setDestination(((JumpInstruction) target).getDestination());
						instructions.remove(target);
					}

					if (jump.getDestination() < instructions.size()) {// recheck to make sure our branch is still jumping within the code
						LabelInstruction src = labels[i] = new LabelInstruction(pool, i);
						LabelInstruction label = labels[jump.getDestination()] = new LabelInstruction(pool, jump.getDestination());

						src.setAttribute("root", false);
						label.setAttribute("root", true);
					} else {
						instructions.remove(jump);
					}
				} else {
					instructions.remove(jump);
				}
			}
		}

		LabelInstruction sink = labels[instructions.size()] = new LabelInstruction(pool, instructions.size());
		sink.setAttribute("root", true);
		sink.setAttribute("sink", true);

		for (LabelInstruction label : labels) {
			if (label != null) {
				instructions.add(label);
			}
		}
	}

	public LabelInstruction next(LabelInstruction start) {
		for (int i = start.getStartPc() + 1; i < labels.length; i++) {
			if (labels[i] != null) {
				return labels[i];
			}
		}
		return null;
	}

	public LabelInstruction getSink() {
		for (LabelInstruction label : labels) {
			if (label == null) {
				continue;
			}

			Object sink = label.getAttribute("sink");
			if (sink != null && (boolean) sink) {
				return label;
			}
		}
		return null;
	}

}
