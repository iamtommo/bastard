package com.bastard.code.block;

import com.bastard.instruction.impl.LabelInstruction;

/**
 * Represents a set of instructions in a code block.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CodeBlock extends Block {

	private LabelInstruction start;
	private String tag = "none";

	public CodeBlock(LabelInstruction start) {
		this.start = start;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}

	public LabelInstruction getStart() {
		return start;
	}
	
	@Override
	public String toString() {
		return "CodeBlock[tag="+tag+", start="+start+"]";
	}
}
