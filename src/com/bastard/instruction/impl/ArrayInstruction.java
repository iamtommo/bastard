package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;

public class ArrayInstruction extends Instruction {

	private int typeIndex;
	
	public ArrayInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		this.typeIndex = code.getShort();
		return this;
	}

	@Override
	public String toString() {
		return "ArrayInstruction[typeIndex="+typeIndex+"]";
	}

}
