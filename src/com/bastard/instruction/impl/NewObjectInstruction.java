package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class NewObjectInstruction extends Instruction {
	
	/**
	 * The index of the class reference in the constant pool
	 */
	private int classRefIndex;

	public NewObjectInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		classRefIndex = code.getShort();
		System.out.println("\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		return "NewObjectInstruction[" + Opcode.valueOf(getOpcode()).toString() + ", index=" + classRefIndex + "]";
	}

}
