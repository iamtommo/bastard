package com.bastard.code;

import java.util.ArrayList;
import java.util.List;

import com.bastard.instruction.Instruction;

public class Node {

	protected Node parent;
	protected List<Node> children = new ArrayList<>();
	protected Instruction instruction;
	
	public Node(Instruction instruction) {
		this.instruction = instruction;
	}
	
	public Instruction getInstruction() {
		return instruction;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void addChild(Node node) {
		node.parent = this;
		children.add(node);
	}
	
	@Override
	public String toString() {
		return "Node[instruction="+instruction+", parent="+parent+", children="+children.size()+"]";
	}
}
