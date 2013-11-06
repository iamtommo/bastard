package com.bastard.code;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

/**
 * A single ended node, otherwise known as a node who
 * operates with a single operand.
 * 
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class SinglyEndedNode extends Node {
	
	/**
	 * The node.
	 */
	protected Node node;

	public SinglyEndedNode(ConstantPool pool, Instruction instruction, Node node) {
		super(pool, instruction);
		this.node = node;
		
		addChild(node);
	}
	
	public Node getNode() {
		return node;
	}
	
}
