package com.bastard.code.impl;

import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
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
	public String code() {
		return "LocalVariableNode[index="+index+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).toString()+", children="+children.size()+"]";
	}
}
