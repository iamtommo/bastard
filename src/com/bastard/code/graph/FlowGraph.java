package com.bastard.code.graph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

	/**
	 * The labels of this method.
	 */
	private LabelInstruction[] labels;
	/**
	 * The sink block.
	 */
	private CodeBlock sink;
	/**
	 * The source block.
	 */
	private CodeBlock source;

	public FlowGraph(CodeAttribute code) {
		super(code);
		this.labels = new LabelInstruction[code.getInstructionList().size() + 1];
	}

	@Override
	public void construct() {
		createLabels();
		createBaseBlocks();
		try {
			toDot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			if (label != null && !vertices.containsKey(label)) {
				if ((boolean) label.getAttribute("root")) {

					CodeBlock block = new CodeBlock(label);

					if (block.getTag() == null) {
						block.setTag("ROOT[index="+vertices.size()+"]");
					}

					if (first == null) {
						first = block;
					}

					addVertex(label, block);
				} else {
					CodeBlock root = root(label);
					if (root != null) {
						CodeBlock block = new CodeBlock(label);

						block.setTag("PATH[index="+vertices.size()+"]");
						addVertex(label, block);
						addEdge(root, block);
					}
				}
			}
		}

		addVertex(sink.getKey(), sink); // Add the sink block at the end, rather than when it was created
		// in an attempt to maintain some order.

		if (code.getCodeLength() == 0) {
			addEdge(source, sink);
			return;
		}

		InstructionList instructions = code.getInstructionList().copy();

		addEdge(source, first);
		/*
		 * For each block, we need to specify the instructions which are contained
		 * within each block.
		 */
		for (CodeBlock block : vertices.values()) {
			if (block == source || block == sink) {
				continue;
			}

			InstructionList sliced = instructions.slice(slice(instructions, block));

			block.setScope(sliced);
		}

		/*
		 * Iterate code to find jump instructions and calculate each of their
		 * destinations relative to the code block the instruction is contained within,
		 * rather than just the index of the instruction in the code buffer.
		 * 
		 * This has to be executed after code blocks are sliced to ensure that all possible
		 * pathways are availiable.
		 */
		for (Instruction insn : code.getInstructionList()) {
			if (insn instanceof JumpInstruction) {
				JumpInstruction jump = (JumpInstruction) insn;

				if (jump.getDestination() >= code.getInstructionList().size()) {
					continue;
				}

				Instruction target = code.getInstructionList().get(jump.getDestination());

				CodeBlock src = get(jump);
				CodeBlock dst = get(target);

				if (dst != null) {
					addEdge(src, dst);
					jump.setTargetBlock(dst);
					jump.setTargetDestination(dst.getScope().indexOf(target));

					dst.update();// update the stack to reflect these changes.
				}
			}
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
				jump.setTargetBlock(dst);
				jump.setTargetDestination(dst.getKey().getStartPc());

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

	public void toDot() throws IOException {
		BufferedWriter writer = null;
		StringBuilder sb = new StringBuilder("digraph flowgraph {\n");
		try {
			writer = new BufferedWriter(new FileWriter("./graphs/graph-"+System.nanoTime()+".dot"));

			label(source, sb);
			reset(source);
			sb.append("\n\n");
			spline(source, sb);

			sb.delete(sb.length() - 2, sb.length());
			sb.append(";\n}");
		} finally {
			writer.write(sb.toString());
			writer.close();
		}
	}

	public void spline(CodeBlock block, StringBuilder sb) {
		if (!block.isVisited()) {
			block.setVisited(true);
			for (Vertex<LabelInstruction> v : block.successors) {
				CodeBlock successor = (CodeBlock) v;

				if (!successor.isVisited()) {
					for (CodeBlock b : vertices.values()) {
						if (successor.succeeds(b)) {
							sb.append("\t"+b.hashCode()+" -> "+successor.hashCode()+" [dir=forward color=\"red\" splines=\"ortho\"];\n");
						}
					}
					spline(successor, sb);
				}
			}
		}
	}

	public void reset(CodeBlock block) {
		if (block.isVisited()) {
			block.setVisited(false);

			for (Vertex<LabelInstruction> v : block.successors) {
				CodeBlock successor = (CodeBlock) v;

				if (successor.isVisited()) {
					reset(successor);
				}
			}
		}
	}

	public void label(CodeBlock block, StringBuilder sb) {
		if (!block.isVisited()) {
			block.setVisited(true);
			sb.append("\t"+block.hashCode()+" [label=\""+block.getTag()+", degrees=[in="+block.predecessors.size()+", out="+block.successors.size()+"]\"");

			if (block == source || block == sink) {
				sb.append(" shape=box style=\"filled\" color=\"orange\"");
			} else if (block.getTag().contains("PATH")) {
				sb.append(" shape=polygon sides=5 style=\"filled\" color=\"grey\"");
			} else if (block.getTag().contains("ROOT")) {
				sb.append(" style=\"filled\" color=\"brown\"");
			}
			sb.append("];\n");
			for (Vertex<LabelInstruction> v : block.successors) {
				CodeBlock successor = (CodeBlock) v;

				if (!successor.isVisited()) {
					label(successor, sb);
				}
			}
		}
	}

	/**
	 * Searches through each label for the label marked as the sink label.
	 * @return The sink label.
	 */
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


	/**
	 * Checks each {@link CodeBlock} for a specific instruction, and if the instruction
	 * is found, return the code block that the instruction was found in.
	 * @param instruction The instruction to find.
	 * @return The {@link CodeBlock} containing the instruction.
	 */
	private CodeBlock get(Instruction instruction) {
		for (CodeBlock block : vertices.values()) {
			if (block == source || block == sink) {
				continue;
			}

			if (block.getScope().contains(instruction)) {
				return block;
			}
		}
		return null;
	}

	/**
	 * Checks each label starting from a specified label
	 * and working backwards until a label with a root property is found.
	 * The code block the label represents is then returned.
	 * @param label The label to begin the search from.
	 * @return The {@link CodeBlock} root.
	 */
	private CodeBlock root(LabelInstruction label) {
		for (int i = label.getStartPc() - 1; i >= 0; i--) {
			LabelInstruction root = labels[i];
			if (root != null && (boolean) root.getAttribute("root")) {
				return vertices.get(root);
			}
		}
		return null;
	}

}
