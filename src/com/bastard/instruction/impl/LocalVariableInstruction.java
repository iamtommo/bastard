package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class LocalVariableInstruction extends Instruction {

	/**
	 * The index of the local variable if applicable
	 */
	private int index;

	public LocalVariableInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		this.index = code.get();
		return this;
	}

	@Override
	public String toString() {
		return "LocalVariableInstruction[index="+index+"]";
	}

}
