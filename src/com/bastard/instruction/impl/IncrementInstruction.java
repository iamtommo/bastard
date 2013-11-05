package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class IncrementInstruction extends Instruction {

	private int increment;
	public IncrementInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		this.increment = code.getShort();
		return this;
	}

	@Override
	public String toString() {
		return "IncrementInstruction[increment="+increment+"]";
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}

}
