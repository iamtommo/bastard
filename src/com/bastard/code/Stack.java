package com.bastard.code;

import com.bastard.code.impl.ArithmeticNode;
import com.bastard.code.impl.FieldNode;
import com.bastard.code.impl.JumpNode;
import com.bastard.code.impl.LdcNode;
import com.bastard.code.impl.LocalVariableNode;
import com.bastard.code.impl.MethodNode;
import com.bastard.code.impl.PushNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.InstructionList;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.ArithmeticInstruction;
import com.bastard.instruction.impl.BasicInstruction;
import com.bastard.instruction.impl.FieldInstruction;
import com.bastard.instruction.impl.JumpInstruction;
import com.bastard.instruction.impl.LdcInstruction;
import com.bastard.instruction.impl.LocalVariableInstruction;
import com.bastard.instruction.impl.MethodInstruction;
import com.bastard.instruction.impl.PushInstruction;

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
			
			if (instruction instanceof LocalVariableInstruction) {
				push(new LocalVariableNode((LocalVariableInstruction) instruction));
				continue;
			}

			if (instruction instanceof FieldInstruction) {
				push(new FieldNode(instructions.getConstantPool(), (FieldInstruction) instruction));
				continue;
			}
			
			if (instruction instanceof MethodInstruction) {
				push(new MethodNode(instructions.getConstantPool(), (MethodInstruction) instruction));
				continue;
			}
			
			if (instruction instanceof LdcInstruction) {
				push(new LdcNode(instructions.getConstantPool(), (LdcInstruction) instruction));
				continue;
			}
			
			if (instruction instanceof PushInstruction) {
				push(new PushNode(instructions.getConstantPool(), (PushInstruction) instruction));
				continue;
			}
			
			if (instruction instanceof ArithmeticInstruction) {
				Instruction left = instructions.get(i - 2);
				Instruction right = instructions.get(i - 1);

				push(new ArithmeticNode(instruction, left.toNode(), right.toNode()));
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

		if (node instanceof DoublyEndedNode) {
			DoublyEndedNode de = (DoublyEndedNode) node;

			push(de.getLeft());
			push(de.getRight());
		}

		if (node instanceof ArithmeticNode) {// pop off the original left/right nodes
			stack.pop();
			stack.pop();
		}

		stack.push(node);
		if (root != node && node.getParent() == null) {
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
				System.out.println("\t\t\t\t\t[ROOT]"+node.code());
			} else {
				System.out.println("\t\t\t\t\t\t     "+node.code());
			}
		}
		System.out.println("\t\t\t\t}");
	}

	@Override
	public String toString() {
		return "Stack[size="+stack.size()+", instructions="+instructions.size()+", root="+root.code()+"]";
	}

	public InstructionList getInstructions() {
		return instructions;
	}


}
