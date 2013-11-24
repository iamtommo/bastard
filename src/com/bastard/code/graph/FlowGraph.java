package com.bastard.code.graph;

import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.code.graph.block.CodeBlock;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.BasicInstruction;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;
import com.bastard.instruction.impl.ThrowInstruction;

/**
 * A representation of the program's control flow. 
 * When complete, this graph will represent all possible entry/exit points
 * of the local method code.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class FlowGraph extends Graph<LabelInstruction, CodeBlock> {

	private LabelInstruction[] labels;
	private CodeBlock sink;
	private CodeBlock source;

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
		source = new CodeBlock(null);
		source.setTag("SOURCE");

		addVertex(null, source);

		sink = new CodeBlock(getSink());
		sink.setTag("SINK");

		CodeBlock first = null;

		for (LabelInstruction label : labels) {
			if (label != null && (boolean) label.getAttribute("root")) {

				CodeBlock block = new CodeBlock(label);

				if (block.getTag() == null) {
					block.setTag("[index="+vertices.size()+"]");
				}

				if (first == null) {
					first = block;
				}

				addVertex(label, block);
			}
		}

		addVertex(sink.getKey(), sink); // Add the sink block at the end, rather than when it was created
		// in an attempt to maintain some order.

		addEdge(source, sink);
		if (code.getCodeLength() == 0) {
			return;
		}

		InstructionList instructions = code.getInstructionList().copy();
		
		addEdge(source, first);
		for (CodeBlock block : vertices.values()) {
			if (block == source || block == sink) {
				continue;
			}

			InstructionList sliced = instructions.slice(slice(instructions, block));
			
			block.setScope(sliced);
		}
	}

	/**
	 * Slices the raw code instructions up into chunks of instructions
	 * that represent the set of instructions within a code block.
	 * @param instructions The instructions list.
	 * @param block The code block to slice instructions for.
	 * @return The list of sliced instructions.
	 */
	public InstructionList slice(InstructionList instructions, CodeBlock block) {
		InstructionList list = instructions.subList(0, instructions.size());
		for (int i = 0; i < instructions.size(); i++) {
			Instruction insn = instructions.get(i);

			if (insn instanceof JumpInstruction) {
				JumpInstruction jump = (JumpInstruction) insn;

				if (jump.getDestination() >= labels.length) {
					block.warn("Code attempts to jump beyond method bounds: opcode("+Opcode.valueOf(jump.getOpcode() & 0xFF)+") with dst("+jump.getDestination()+") >= labels("+labels.length+")");
					return list.subList(0, i + 1);
				}
				
				CodeBlock dst = vertices.get(labels[jump.getDestination()]);
				addEdge(block, dst);
				return list.subList(0, i + 1);
			}

			if (insn instanceof LabelInstruction) {

				InstructionList sub = list.subList(0, i + 1);

				CodeBlock dst = vertices.get(labels[((LabelInstruction) insn).getStartPc()]);

				JumpInstruction jump = new JumpInstruction(pool, Opcode.GOTO.getOpcode());
				jump.setDestination(dst.getKey().getStartPc());

				addEdge(block, dst);

				sub.set(sub.indexOf(insn), jump);
				return sub;
			}

			if (insn.toString().contains("RET") || insn instanceof ThrowInstruction) {
				addEdge(block, sink);
				return list.subList(0, i + 1);
			}
		}
		
		return instructions;
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
					} else if (jump.getOpcode() == Opcode.GOTO.getOpcode() && target.toString().contains("RET")) {
						instructions.set(i, new BasicInstruction(pool, target.getOpcode()));
					} else {
						if (jump.getDestination() < instructions.size()) {// recheck to make sure our branch is still jumping within the code
							LabelInstruction src = labels[i] = new LabelInstruction(pool, i);
							LabelInstruction label = labels[jump.getDestination()] = new LabelInstruction(pool, jump.getDestination());

							src.setAttribute("root", false);
							label.setAttribute("root", true);
						} else {
							instructions.remove(jump);
						}
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
