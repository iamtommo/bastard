package com.bastard.code.impl;

import java.util.Arrays;

import com.bastard.code.Node;

/**
 * Represents a single invocation of a method, and it's parameters.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CallMethodNode extends Node {

	private MethodNode node;
	private Node[] parameters;
	
	public CallMethodNode(MethodNode node) {
		super(node.getPool(), node.getInstruction());
		this.node = node;
		this.parameters = node.getChildren().toArray(new Node[0]);
		
		for (Node parameter : parameters) {
			parameter.setParent(this);
		}
		for (Node child : node.getChildren()) {
			addChild(child);
		}
	}
	
	public MethodNode getNode() {
		return node;
	}

	public Node[] getParameters() {
		return parameters;
	}
	
	@Override
	public String code() {
		return "CallMethodNode[method="+node.code()+", parameters="+Arrays.toString(parameters)+"]";
	}

}
