package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

public class MultiArrayInstruction extends Instruction {

	private int index;
	private int dimensions;
	
	public MultiArrayInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		this.index = code.getShort();
		return this;
	}

	@Override
	public String toString() {
		return "MultiArrayInstruction[index="+index+", dimensions="+dimensions+"]";
	}

}
