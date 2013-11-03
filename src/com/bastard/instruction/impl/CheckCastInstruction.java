package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class CheckCastInstruction extends Instruction {
	
	/**
	 * The index in the constant pool of the class reference this instruction is checking a cast for
	 */
	private int classRefIndex;

	public CheckCastInstruction(int opcode) {
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
		return "CheckCastInstruction[index=" + classRefIndex + "]";
	}

}
