package com.bastard.code;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

/**
 * A single ended node, otherwise known as a node who
 * operates with two operands.
 * 
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class DoublyEndedNode extends Node {
	
	/**
	 * The left operand.
	 */
	protected Node left;
	/**
	 * The right operand.
	 */
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
