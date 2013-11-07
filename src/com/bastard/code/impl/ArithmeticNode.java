package com.bastard.code.impl;

import com.bastard.code.DoublyEndedNode;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class ArithmeticNode extends DoublyEndedNode {

	public ArithmeticNode(Instruction instruction, Node left, Node right) {
		super(null, instruction, left, right);
		System.out.println(compute());
	}
	
	/**
	 * Attempts to compute the arithmetic operation between each node end.
	 * @return The computed number, or null if there was a failed computation.
	 */
	public Number compute() {
		
		Number leftValue = null;
		Number rightValue = null;
		
		if (left instanceof LdcNode) {
			leftValue = (Number) ((LdcNode)left).getConstant();
		} else if (left instanceof PushNode) {
			leftValue = (Number) ((PushNode)left).getValue();
		} else if (left instanceof FieldNode) {
			
		}
		
		if (right instanceof LdcNode) {
			rightValue = (Number) ((LdcNode)left).getConstant();
		} else if (right instanceof PushNode) {
			rightValue = (Number) ((PushNode)left).getValue();
		} else if (right instanceof FieldNode) {
			
		}
		
		if (leftValue == null || rightValue == null) {
			return null;
		}
		
		if (getOperation().equals("/") && rightValue.longValue() == 0) {
			throw new IllegalStateException("Divide by 0");
		}
		
		switch(getOperation()) {
		case "+":
			return leftValue.longValue() + rightValue.longValue();
		case "-":
			return leftValue.longValue() - rightValue.longValue();
		case "*":
			return leftValue.longValue() * rightValue.longValue();
		case "/":
			return leftValue.longValue() / rightValue.longValue();
		case "<<":
			return leftValue.longValue() << rightValue.longValue();
		case ">>":
			return leftValue.longValue() >> rightValue.longValue();
		case ">>>":
			return leftValue.longValue() >>> rightValue.longValue();
		case "&":
			return leftValue.longValue() & rightValue.longValue();
		case "|":
			return leftValue.longValue() | rightValue.longValue();
		case "^":
			return leftValue.longValue() ^ rightValue.longValue();
		}
		return leftValue.longValue() + rightValue.longValue();
	}

	public String getOperation() {
		String name = Opcode.valueOf(instruction.getOpcode()).toString();
		if (name.contains("ADD")) {
			return "+";
		}
		
		if (name.contains("SUB")) {
			return "-";
		}
		
		if (name.contains("MUL")) {
			return "*";
		}
		
		if (name.contains("DIV")) {
			return "/";
		}
		
		if (name.contains("SHL")) {
			return "<<";
		}
		
		if (name.contains("USHR")) {
			return ">>>";
		}
		
		if (name.contains("SHR")) {
			return ">>";
		}
		
		if (name.contains("AND")) {
			return "&";
		}
		
		if (name.contains("XOR")) {
			return "^";
		}
		
		if (name.contains("OR")) {
			return "|";
		}
		return "nop "+name;
	}
	
	@Override
	public String code() {
		return "ArithmeticNode[left="+left.code()+" "+getOperation()+" right="+right.code()+", children="+children.size()+"]";
	}

}
