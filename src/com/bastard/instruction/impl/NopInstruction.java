package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class NopInstruction extends Instruction {

	public NopInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		System.out.println("\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		return "NopInstruction[]";
	}

}
