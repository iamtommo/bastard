package com.bastard.code.graph;

import java.util.ArrayList;
import java.util.List;

import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.code.block.CodeBlock;
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
public class FlowGraph extends Graph {

	private LabelInstruction[] labels;

	private List<CodeBlock> blocks = new ArrayList<>();
	
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
	 * the next availiable label becomes the new endLabel, until the endLabel 
	 * is the sink.
	 */
	public void createBaseBlocks() {
		CodeBlock source = new CodeBlock(labels[0], getSink());
		source.setTag("SOURCE");
		
		LabelInstruction start = next(source.getStart());
		LabelInstruction end;
		while((end = next(start)) != null && end != source.getEnd()) {
			source.addBranch(new CodeBlock(start, end));
			
			start = end;
		}
		CodeBlock sink = new CodeBlock(source.getEnd(), source.getEnd());
		sink.setTag("SINK");
		
		blocks.add(source);
		blocks.add(sink);
		
		
		System.out.println(source);
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
					} else if (target.toString().contains("RET")) {// branch Jump -> return to Return
						instructions.set(i, target);
					}

					if (jump.getDestination() < instructions.size()) {// recheck to make sure our branch is still jumping within the code
						LabelInstruction label = labels[jump.getDestination()] = new LabelInstruction(pool, jump.getDestination());
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
