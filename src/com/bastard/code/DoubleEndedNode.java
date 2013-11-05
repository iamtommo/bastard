package com.bastard.code;

import com.bastard.instruction.Instruction;

public class DoubleEndedNode extends Node {
	
	protected Node left;
	protected Node right;

	public DoubleEndedNode(Instruction instruction, Instruction left, Instruction right) {
		super(instruction);
		this.left = new Node(left);
		this.right = new Node(right);
		
		addChild(this.left);
		addChild(this.right);
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
}
