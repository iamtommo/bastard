package com.bastard.code;

import java.util.ArrayList;
import java.util.List;

import com.bastard.cls.cpool.ConstantPool;
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

	/**
	 * A reference to the constant pool.
	 */
	protected ConstantPool pool;

	public Node(ConstantPool pool, Instruction instruction) {
		this.pool = pool;
		this.instruction = instruction;
	}

	/**
	 * Adds a node to the hierarchy as a child, and then
	 * sets that node's parent to this node.
	 * @param node The child node.
	 */
	public void addChild(Node node) {
		node.setParent(this);
		if (!children.contains(node)) {
			children.add(node);
		}
	}

	/**
	 * Makes a new copy of this node.
	 */
	public Node clone() {
		Node clone = new Node(pool, instruction);
		clone.setParent(parent);
		clone.children = children;
		return clone;
	}

	public void setParent(Node parent) {
		if (this.parent != null) {
			this.parent.removeChild(this);
		}
		this.parent = parent;
	}

	public void removeChild(Node node) {
		Node parent = node;
		while((parent = parent.parent) != null) {
			if (parent.children.contains(node)) {
				parent.children.remove(node);
			}
		}
		if (this.parent != null) {
			this.parent.removeChild(this);
		}
	}

	public Node getParent() {
		return parent;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public String code() {
		return "Node[instruction="+instruction+", children="+children.size()+"]";
	}

	public ConstantPool getPool() {
		return pool;
	}

	public List<Node> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return code();
	}
}
