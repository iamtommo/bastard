package com.bastard.code.impl;

import com.bastard.code.BidirectionalNode;
import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;

/**
 * 
 * Represents if statement instructions.
 * @author Shawn Davies
 */
public class IfNode extends BidirectionalNode {

	/**
	 * The jump destination.
	 * TODO: Change this to Node and define by getting the node at JumpInstruction#jumpLocation.
	 */
	private int dst;
	private LabelInstruction label;
	
	public IfNode(JumpInstruction instruction, Node left, Node right) {
		super(instruction.getPool(), instruction, left, right);
		this.dst = instruction.getDestination();
	}
	
	public String getComparison() {
		return instruction.toString().contains("EQ") ? "EQUAL" : instruction.toString().contains("NE") ? "NOT EQUAL" : "NOT COMPARISON";
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
		return "IfNode[dst="+dst+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).name()+", left="+left.code()+", right="+right.code()+", label="+label+"]";
	}
}
