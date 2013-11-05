package com.bastard.code.impl;

import com.bastard.code.Node;
import com.bastard.instruction.impl.LocalVariableInstruction;

/**
 * Represents a local variable as a node.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class LocalVariableNode extends Node {

	/**
	 * The index this local variable is assigned to.
	 */
	private int index;
	
	public LocalVariableNode(LocalVariableInstruction instruction) {
		super(null, instruction);
		this.index = instruction.getIndex();
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return "LocalVariableNode[index="+index+", parent="+parent+", children="+children.size()+"]";
	}
}
