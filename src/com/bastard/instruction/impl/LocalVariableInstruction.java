package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

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
		if (getOpcode() == Opcode.ISTORE.getOpcode()) {
			this.index = code.getShort();
		}
		
		return this;
	}

	@Override
	public String toString() {
		return "LocalVariableInstruction[]";
	}

	public int getIndex() {
		return index;
	}

}
