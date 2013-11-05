package com.bastard.instruction;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;

/**
 * An abstract bytecode instruction.
 * @author Tommo
 *
 */
public abstract class Instruction {
	
	private int opcode;
	protected ConstantPool pool;
	
	public ConstantPool getPool() {
		return pool;
	}

	public Instruction(ConstantPool pool, int opcode) {
		this.pool = pool;
		this.opcode = opcode;
	}
	
	public int getOpcode() {
		return opcode;
	}
	
	public abstract Instruction read(ConstantPool pool, ByteBuffer code);
	
	public abstract Node toNode();
	
	@Override
	public abstract String toString();

}
