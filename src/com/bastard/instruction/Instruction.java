package com.bastard.instruction;

import java.nio.ByteBuffer;

/**
 * An abstract bytecode instruction.
 * @author Tommo
 *
 */
public abstract class Instruction {
	
	private int opcode;
	
	public Instruction(int opcode) {
		this.opcode = opcode;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public abstract Instruction read(ByteBuffer code);
	
	@Override
	public abstract String toString();

}
