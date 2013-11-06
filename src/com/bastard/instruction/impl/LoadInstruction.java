package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;


public class LoadInstruction extends Instruction {

	public LoadInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "LoadInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "LoadInstruction[op=" + opcode.toString() + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(null, this);
	}

}
