package com.bastard.code.impl;

import com.bastard.code.Node;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;

/**
 * 
 * Represents GOTO, GOTO_W, JSR, and JSR_W jump instructions.
 * @author Shawn Davies
 */
public class JumpNode extends Node {

	/**
	 * The jump destination.
	 * TODO: Change this to Node and define by getting the node at JumpInstruction#jumpLocation.
	 */
	private int dst;
	private LabelInstruction label;
	
	public JumpNode(JumpInstruction instruction) {
		super(null, instruction);
		this.dst = instruction.getDestination();
	}
	
	public LabelInstruction getLabel() {
		return label;
	}
	
	public void setLabel(LabelInstruction label) {
		this.label = label;
	}
	
	public int getDestination() {
		return dst;
	}
	
	@Override
	public String code() {
		return "JumpNode[dst="+dst+", label="+label+", children="+children.size()+"]";
	}

	public void setDestination(int dst) {
		this.dst = dst;
	}
}
