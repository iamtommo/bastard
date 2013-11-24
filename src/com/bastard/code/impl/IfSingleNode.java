package com.bastard.code.impl;

import com.bastard.code.DirectionalNode;
import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;

/**
 * 
 * Represents if statement instructions like IFNONNULL which
 * take a single parameter.
 * @author Shawn Davies
 */
public class IfSingleNode extends DirectionalNode {

	/**
	 * The jump destination.
	 * TODO: Change this to Node and define by getting the node at JumpInstruction#jumpLocation.
	 */
	private int dst;
	private LabelInstruction label;
	
	public IfSingleNode(JumpInstruction instruction, Node node) {
		super(instruction.getPool(), instruction, node);
		this.dst = instruction.getDestination();
	}
	
	public int getDestination() {
		return dst;
	}
	
	public LabelInstruction getLabel() {
		return label;
	}
	
	public void setLabel(LabelInstruction label) {
		this.label = label;
	}
	
	public void setDestination(int dst) {
		this.dst = dst;
	}

	@Override
	public String code() {
		return "IfSingleNode[dst="+dst+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).name()+", node="+node+"]";
	}
}
