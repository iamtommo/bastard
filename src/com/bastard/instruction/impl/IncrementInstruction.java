package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class IncrementInstruction extends Instruction {

	private int increment;
	public IncrementInstruction(int opcode) {
		super(opcode);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Instruction read(ByteBuffer code) {
		this.increment = code.getShort();
		return this;
	}

	@Override
	public String toString() {
		return "IncrementInstruction[increment="+increment+"]";
	}

}
