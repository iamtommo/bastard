package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

public class NewObjectInstruction extends Instruction {
	
	/**
	 * The index of the class reference in the constant pool
	 */
	private int classRefIndex;

	public NewObjectInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		classRefIndex = code.getShort();
		System.out.println("\t\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "NewObjectInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "NewObjectInstruction[" + opcode.toString() + ", index=" + classRefIndex + "]";
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}
}
