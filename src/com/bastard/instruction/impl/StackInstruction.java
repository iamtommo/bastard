package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class StackInstruction extends Instruction {

	public StackInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "StackInstruction[op=" + Opcode.valueOf(getOpcode()).toString() + "]";
	}

}
