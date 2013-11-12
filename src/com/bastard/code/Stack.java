package com.bastard.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bastard.code.impl.ArithmeticNode;
import com.bastard.code.impl.CallMethodNode;
import com.bastard.code.impl.CastNode;
import com.bastard.code.impl.FieldNode;
import com.bastard.code.impl.IfNode;
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
import com.bastard.util.Indent;

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
				stack.roots.put(n, n);
				stack.root = n;
			}
			return n;
		}

		@Override
		public Node pop() {
			Node node = super.pop();

			if (stack.root != null) {
				stack.root.removeChild(node);
			}
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
	 * The current root node of the stack.
	 */
	private Node root;
	/**
	 * A mapping of last nodes to their roots.
	 */
	private final Map<Node, Node> roots = new HashMap<Node, Node>();
	
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
				boolean isReturn = instruction.toString().contains("RET");
				
				if (!stack.isEmpty() && stack.peek() instanceof JumpNode && isReturn) {//simplifies Jump Return to just Return
					stack.pop();
				}
				
				push(new Node(instructions.getConstantPool(), instruction));
				
				if (isReturn) {
					root = null;
				}
				continue;
			}

			if (instruction instanceof LocalVariableInstruction) {
				push(new LocalVariableNode((LocalVariableInstruction) instruction));
				continue;
			}

			if (instruction instanceof FieldInstruction) {
				FieldNode node = new FieldNode(instructions.getConstantPool(), (FieldInstruction) instruction);
				if (node.instruction.toString().contains("PUT")) {
					node.addChild(stack.pop());
				}
				push(node);
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
				push(new CastNode(instructions.getConstantPool(), instruction.toNode(), stack.pop(), stack.pop()));
				continue;
			}
			
			if (instruction instanceof ArithmeticInstruction) {
				push(new ArithmeticNode(instruction, stack.pop(), stack.pop()));
				continue;
			}

			if (instruction instanceof JumpInstruction) {
				String name = Opcode.valueOf(instruction.getOpcode() & 0xFF).toString();

				if (name.contains("GOTO") || name.contains("JSR")) {
					
					if (!stack.isEmpty() && stack.peek() instanceof JumpNode) {// simplifies redundant jump nodes (jumping to another jump node). 
						stack.pop();
					}
					
					push(new JumpNode((JumpInstruction) instruction));
				} else if (name.contains("IF")) {
					push(new IfNode((JumpInstruction) instruction, stack.pop(), stack.pop()));
				}
				continue;
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
		stack.push(node);
		
		if (root != node && node.getParent() == null) {
			root.addChild(node);
		}
	}

	/**
	 * Print the contents of this stack.
	 */
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		System.out.println(Indent.$(indentations) + "{");
		for (Node node : stack) {
			if (roots.containsValue(node)) {
				System.out.println(Indent.$(indentations + 1) + "[ROOT]"+roots.get(node));
				System.out.println(Indent.$(indentations + 1) + "{");

				for (Node child : roots.get(node).children) {
					System.out.println(Indent.$(indentations + 2) + child.toString());
				}
				System.out.println(Indent.$(indentations + 1) + "}");
			}
		}
		System.out.println(Indent.$(indentations) + "}");
	}
	
	public int size() {
		return stack.size();
	}

	@Override
	public String toString() {
		int compression = (int) (((double)stack.size() / (double)instructions.size()) * 100D);
		return "Stack[size="+stack.size()+", instructions="+instructions.size()+", roots="+roots.size()+", compression="+compression+"%]";
	}

	public InstructionList getInstructions() {
		return instructions;
	}


	public Node get(int index) {
		return stack.get(index);
	}


}
