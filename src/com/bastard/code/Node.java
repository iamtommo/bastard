package com.bastard.code;

import java.util.ArrayList;
import java.util.List;

import com.bastard.instruction.Instruction;

/**
 * Represents a node on the stack.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class Node {

	/**
	 * This node's parent.
	 */
	protected Node parent;
	/**
	 * The list of nodes that fall below this node in hierarchy.
	 */
	protected List<Node> children = new ArrayList<>();
	/**
	 * The instruction this node was constructed from.
	 */
	protected Instruction instruction;
	
	public Node(Instruction instruction) {
		this.instruction = instruction;
	}
	
	/**
	 * Adds a node to the hierarchy as a child, and then
	 * sets that node's parent to this node.
	 * @param node The child node.
	 */
	public void addChild(Node node) {
		node.setParent(this);
		children.add(node);
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public Instruction getInstruction() {
		return instruction;
	}
	
	@Override
	public String toString() {
		return "Node[instruction="+instruction+", parent="+parent+", children="+children.size()+"]";
	}
}
