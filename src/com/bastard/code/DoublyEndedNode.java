package com.bastard.code;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

public class DoublyEndedNode extends Node {
	
	protected Node left;
	protected Node right;

	public DoublyEndedNode(ConstantPool pool, Instruction instruction, Node left, Node right) {
		super(pool, instruction);
		this.left = left;
		this.right = right;
		
		addChild(left);
		addChild(right);
	}
	
	public Node getLeft() {
		return left;
	}
	
	public Node getRight() {
		return right;
	}
	
}
