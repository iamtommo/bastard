package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class NopInstruction extends Instruction {

	public NopInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "NopInstruction[]";
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}
}
