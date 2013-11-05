package com.bastard.code;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

public class DoubleEndedNode extends Node {
	
	protected Node left;
	protected Node right;

	public DoubleEndedNode(ConstantPool pool, Instruction instruction, Instruction left, Instruction right) {
		super(pool, instruction);
		this.left = new Node(pool, left);
		this.right = new Node(pool, right);
		
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
