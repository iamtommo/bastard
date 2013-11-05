package com.bastard.instruction;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

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
	
	public abstract Instruction read(ConstantPool pool, ByteBuffer code);
	
	@Override
	public abstract String toString();

}
