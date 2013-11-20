package com.bastard.code.graph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.impl.LabelInstruction;
import com.bastard.util.Indent;

/**
 * Represents a set of instructions in a code block.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CodeBlock extends Block {

	private InstructionList instructions;
	private LabelInstruction start;
	private String tag = "none";
	
	private List<CodeBlock> branches = new ArrayList<>();

	public CodeBlock(LabelInstruction start) {
		this.start = start;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public LabelInstruction getStart() {
		return start;
	}

	public void addBranch(CodeBlock block) {
		branches.add(block);
	}
	
	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		
		if (instructions != null) {
			for (Instruction insn : instructions) {
				insn.print(indentations + 1);
			}
		}
		
		for (CodeBlock block : branches) {
			block.print(indentations + 1);
		}
	}
	
	@Override
	public String toString() {
		return "CodeBlock[tag="+tag+", start="+start+", branches="+Arrays.toString(branches.toArray(new CodeBlock[0]))+"]";
	}

	public InstructionList getInstructions() {
		return instructions;
	}

	public void setInstructions(InstructionList instructions) {
		this.instructions = instructions;
	}
	
}
