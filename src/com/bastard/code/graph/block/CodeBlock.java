package com.bastard.code.graph.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.bastard.instruction.impl.LabelInstruction;

/**
 * Represents a set of instructions in a code block.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CodeBlock extends Block {

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
	public String toString() {
		return "CodeBlock[tag="+tag+", start="+start+", branches="+Arrays.toString(branches.toArray(new CodeBlock[0]))+"]";
	}
}
