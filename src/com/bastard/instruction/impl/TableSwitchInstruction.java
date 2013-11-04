package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;

public class TableSwitchInstruction extends Instruction {

	public TableSwitchInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ByteBuffer code) {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

}
