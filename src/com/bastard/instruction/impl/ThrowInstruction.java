package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class ThrowInstruction extends Instruction {

	public ThrowInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "ThrowInstruction";
	}

}
