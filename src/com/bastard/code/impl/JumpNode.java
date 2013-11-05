package com.bastard.code.impl;

import com.bastard.code.Node;
import com.bastard.instruction.impl.JumpInstruction;

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
	
	public JumpNode(JumpInstruction instruction) {
		super(null, instruction);
		this.dst = instruction.getJumpLocation();
	}
	
	public int getDestination() {
		return dst;
	}
	
	@Override
	public String code() {
		return "JumpNode[dst="+dst+", children="+children.size()+"]";
	}
}
