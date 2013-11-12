package com.bastard.code;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

/**
 * A directional node, otherwise known as a node who
 * operates with a single operand.
 * 
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class DirectionalNode extends Node {
	
	/**
	 * The node.
	 */
	protected Node node;

	public DirectionalNode(ConstantPool pool, Instruction instruction, Node node) {
		super(pool, instruction);
		this.node = node;
		
		addChild(node);
	}
	
	public Node getNode() {
		return node;
	}
	
}
