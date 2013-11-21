package com.bastard.code.graph;

import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.code.graph.block.CodeBlock;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.Opcode;
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
		source = new CodeBlock(labels[0]);
		source.setTag("SOURCE");
		source.setScope(code.getInstructionList());

		addVertex(labels[0], source);

		sink = new CodeBlock(getSink());
		sink.setTag("SINK");

		CodeBlock first = null;

		for (LabelInstruction label : labels) {
			if (label != null && label != source.getKey() && (boolean) label.getAttribute("root")) {

				CodeBlock block = new CodeBlock(label);
				addVertex(label, block);

				if (first == null) {
					first = block;
				}
			}
		}

		addVertex(sink.getKey(), sink); // Add the sink block at the end, rather than when it was created
		// in an attempt to maintain some order.

		addEdge(source, sink);
		addEdge(source, first);

		for (CodeBlock block : vertices.values()) {
			if (block == sink) {
				continue;
			}
			block.setScope(slice(source.getScope(), block));
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
		InstructionList list = new InstructionList();

		list.setConstantPool(code.getInstructionList().getConstantPool());
		for (int i = block.getKey().getStartPc(); i < instructions.size(); i++) {
			Instruction insn = instructions.get(i);

			if (insn instanceof JumpInstruction) {
				CodeBlock target = getVertex(labels[((JumpInstruction) insn).getDestination()]);
				addEdge(block, target);
				
				if (insn.toString().contains("IF")) {
					for (int x = block.getKey().getStartPc() + 1; x < instructions.size(); x++) {
						LabelInstruction label = labels[x];

						if (label == null) {
							continue;
						}
						
						if ((boolean) label.getAttribute("root")) {
							CodeBlock inverse = getVertex(label);
							
							addEdge(block, inverse);
						}
						break;
					}
				}
				
				list.add(insn);
				return list;
			}
			
			if (insn instanceof LabelInstruction) {
				LabelInstruction label = (LabelInstruction) insn;

				if ((boolean) label.getAttribute("root")) {
					CodeBlock target = getVertex(label);

					JumpInstruction jump = new JumpInstruction(pool, Opcode.GOTO.getOpcode());
					jump.setDestination(label.getStartPc());

					list.add(jump);
					addEdge(block, target);
					return list;
				}
			}

			if (insn.toString().contains("RET") || insn instanceof ThrowInstruction) {// TODO support for RET opcode for subroutines.
				list.add(insn);
				addEdge(block, sink);
				return list;
			}
			
			list.add(insn);
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
