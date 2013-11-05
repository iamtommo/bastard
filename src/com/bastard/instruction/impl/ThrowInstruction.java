package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class ThrowInstruction extends Instruction {

	public ThrowInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "ThrowInstruction[]";
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}
}
