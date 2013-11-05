package com.bastard.code.impl;

import com.bastard.code.Node;
import com.bastard.instruction.impl.JumpInstruction;

/**
 * 
 * @author Shawn Davies
 */
public class JumpNode extends Node {

	/**
	 * The jump destination.
	 * TODO: Change this to Node and define by getting the node at JumpInstruction#jumpLocation.
	 */
	private int dst;
	
	public JumpNode(JumpInstruction instruction) {
		super(instruction);
		this.dst = instruction.getJumpLocation();
	}
	
	public int getDestination() {
		return dst;
	}
	
	@Override
	public String toString() {
		return "JumpNode[dst="+dst+"]";
	}
}
