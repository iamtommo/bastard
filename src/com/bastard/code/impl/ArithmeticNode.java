package com.bastard.code.impl;

import com.bastard.code.DoublyEndedNode;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class ArithmeticNode extends DoublyEndedNode {

	public ArithmeticNode(Instruction instruction, Node left, Node right) {
		super(instruction.getPool(), instruction, left, right);
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
