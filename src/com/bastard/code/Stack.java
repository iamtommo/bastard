package com.bastard.code;

import com.bastard.code.impl.ArithmeticNode;
import com.bastard.code.impl.JumpNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.ArithmeticInstruction;
import com.bastard.instruction.impl.BasicInstruction;
import com.bastard.instruction.impl.JumpInstruction;

/**
 * Represents a basic node stack machine. This will continue to
 * be a main focus of the entire project for quite a while.
 * 
 * The main purpose is to organize the raw jvm stack into paired sets of "nodes".
 * From here, we can optimize and refine the look of the stack, which can later be output
 * in class format to produce cleaner bytecode.
 * 
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class Stack {

	/**
	 * The java stack util.
	 */
	private java.util.Stack<Node> stack = new java.util.Stack<>();
	/**
	 * The list of raw instructions.
	 */
	private InstructionList instructions;
	/**
	 * The root node of the stack.
	 */
	private Node root;
	
	public Stack(InstructionList instructions) {
		this.instructions = instructions;
		construct();
	}

	/**
	 * Fills the stack with the most basic form of node pairs.
	 */
	public void construct() {
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instruction = instructions.get(i);

			if (instruction instanceof BasicInstruction) {
				push(new Node(instructions.getConstantPool(), instruction));
				continue;
			}

			if (instruction instanceof ArithmeticInstruction) {
				Instruction left = instructions.get(i - 2);
				Instruction right = instructions.get(i - 1);

				push(new ArithmeticNode(instruction, left, right));
				continue;
			}

			if (instruction instanceof JumpInstruction) {
				String name = Opcode.valueOf(instruction.getOpcode() & 0xFF).toString();

				if (name.contains("GOTO") || name.contains("JSR")) {
					push(new JumpNode((JumpInstruction) instruction));
					continue;
				}
			}
		}
	}

	/**
	 * Pushes nodes fully onto the stack.
	 * @param node The node to push.
	 */
	public void push(Node node) {
		if (root == null) {
			root = node;
			push(root);
			return;
		}

		if (node instanceof DoubleEndedNode) {
			DoubleEndedNode de = (DoubleEndedNode) node;

			push(de.getLeft());
			push(de.getRight());
		}

		if (node instanceof ArithmeticNode) {// pop off the original left/right nodes
			stack.pop();
			stack.pop();
		}

		stack.push(node);
		if (root != node && node.parent == null) {
			root.addChild(node);
		}
	}

	/**
	 * Print the contents of this stack.
	 */
	public void print() {
		System.out.println("\t\t\t\t"+toString()+" {");
		for (Node node : stack) {
			if (node == root) {
				System.out.println("\t\t\t\t\t[ROOT]"+node);
			} else {
				System.out.println("\t\t\t\t\t      "+node);
			}
		}
		System.out.println("\t\t\t\t}");
	}

	@Override
	public String toString() {
		return "Stack[size="+stack.size()+", instructions="+instructions.size()+", root="+root+"]";
	}

	public InstructionList getInstructions() {
		return instructions;
	}


}
