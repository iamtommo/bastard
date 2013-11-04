package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class LdcInstruction extends Instruction {
	
	/**
	 * The index in the constant pool of the constant to push onto the stack
	 */
	private int constantIndex;

	public LdcInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		if (getOpcode() == Opcode.LDC.getOpcode()) {
			constantIndex = code.get();
		} else if (getOpcode() == Opcode.LDC_W.getOpcode() || getOpcode() == Opcode.LDC2_W.getOpcode()) {
			constantIndex = code.getShort();
		}
		System.out.println("\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		return "LdcInstruction[op=" + Opcode.valueOf(getOpcode() & 0xFF).toString() + ", index=" + constantIndex + "]";
	}

}
