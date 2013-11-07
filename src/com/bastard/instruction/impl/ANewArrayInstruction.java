package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class ANewArrayInstruction extends Instruction {
	
	/**
	 * The index in the constant pool of the ClassRefEntry indentifying the array component type
	 */
	private int index;

	public ANewArrayInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		this.index = code.getShort();
		return this;
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}

	@Override
	public String toString() {
		return "ANewArrayInstruction[index=" + index + "]";
	}

}
