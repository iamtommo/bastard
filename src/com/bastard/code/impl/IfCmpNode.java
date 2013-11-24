package com.bastard.code.impl;

import com.bastard.code.BidirectionalNode;
import com.bastard.code.Node;
import com.bastard.code.graph.block.CodeBlock;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LabelInstruction;

/**
 * 
 * Represents comparison if statement instructions.
 * @author Shawn Davies
 */
public class IfCmpNode extends BidirectionalNode {

	/**
	 * The jump destination.
	 * TODO: Change this to Node and define by getting the node at JumpInstruction#jumpLocation.
	 */
	private int dst;
	private CodeBlock targetBlock;
	private LabelInstruction label;
	
	public IfCmpNode(JumpInstruction instruction, Node left, Node right) {
		super(instruction.getPool(), instruction, left, right);
		this.targetBlock = instruction.getTargetBlock();
		this.dst = instruction.getTargetDestination();
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
	
	public CodeBlock getTargetBlock() {
		return targetBlock;
	}
	
	public void setLabel(LabelInstruction label) {
		this.label = label;
	}
	
	public void setDestination(int dst) {
		this.dst = dst;
	}

	@Override
	public String code() {
		if (targetBlock == null) {
			return "IfCmpNode[dst="+dst+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).name()+", left="+left.code()+", right="+right.code()+", label="+label+"]";
		}
		return "IfCmpNode[dst="+dst+", targetBlock="+targetBlock.getTag()+", type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).name()+", left="+left.code()+", right="+right.code()+", label="+label+"]";
	}
}
