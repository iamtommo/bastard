package com.bastard.code.impl;

import com.bastard.code.DoubleEndedNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class ArithmeticNode extends DoubleEndedNode {

	public ArithmeticNode(Instruction instruction, Instruction left, Instruction right) {
		super(instruction, left, right);
	}

	@Override
	public String toString() {
		return "ArithmeticNode[left="+left.getInstruction()+" "+getOperation()+" right="+right.getInstruction()+"]";
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

}
