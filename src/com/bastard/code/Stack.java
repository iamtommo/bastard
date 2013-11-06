package com.bastard.code;

import java.util.List;

import com.bastard.code.impl.ArithmeticNode;
import com.bastard.code.impl.CallMethodNode;
import com.bastard.code.impl.CastNode;
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
import com.bastard.instruction.impl.CastInstruction;
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
	 * A small modification of java.util.Stack, which allows the dynamic updating
	 * of root nodes.
	 * @author Shawn Davies<sodxeh@gmail.com>
	 *
	 * @param <E> The node element.
	 */
	private static final class NodeStack<E> extends java.util.Stack<Node> {
		/**
		 * Default serial uid.
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * The base stack.
		 */
		private Stack stack;

		public NodeStack(Stack stack) {
			super();
			this.stack = stack;
		}

		@Override
		public Node push(Node node) {

			Node n = super.push(node);

			if (stack.root == null) {
				stack.root = n;
			}
			return n;
		}

		@Override
		public Node pop() {
			Node node = super.pop();

			if (stack.root == node) {
				stack.root = null;
			}
			return node;
		}
	}

	/**
	 * The java stack util.
	 */
	private final java.util.Stack<Node> stack = new NodeStack<>(this);
	/**
	 * The list of raw instructions.
	 */
	private final InstructionList instructions;
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
	private void construct() {
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
				push(collapseMethod(new MethodNode(instructions.getConstantPool(), (MethodInstruction) instruction)));
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

			if (instruction instanceof CastInstruction) {
				Instruction left = instructions.get(i - 2);
				Instruction right = instructions.get(i - 1);

				push(new CastNode(instructions.getConstantPool(), instruction.toNode(), left.toNode(), right.toNode()));
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
					JumpNode node = new JumpNode((JumpInstruction) instruction);
					push(node);
					continue;
				}
			}
		}
	}

	/**
	 * Collapses MethodNodes and their parameters into a single node,
	 * which represents a method invocation.
	 */
	private CallMethodNode collapseMethod(MethodNode mn) {
		List<String> types = mn.getTypes();

		int last = types.size() - 1;

		for (int i = 0; i < last; i++) {
			mn.addChild(stack.pop());
		}

		return new CallMethodNode(mn, types.get(last));
	}

	/**
	 * Pushes nodes fully onto the stack.
	 * @param node The node to push.
	 */
	public void push(Node node) {
		if (node instanceof SinglyEndedNode) {// pop off the original nodes.
			stack.pop();
		} else if (node instanceof DoublyEndedNode) {
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
		System.out.println("\t\t\t"+toString()+" {");
		for (Node node : stack) {
			if (root == node) {
				System.out.println("\t\t\t\t[ROOT]"+root.code());
			} else {
				System.out.println("\t\t\t\t\t     "+node.code());
			}
		}
		System.out.println("\t\t\t}");
	}

	@Override
	public String toString() {
		return "Stack[size="+stack.size()+", instructions="+instructions.size()+", root="+root+"]";
	}

	public InstructionList getInstructions() {
		return instructions;
	}


}
