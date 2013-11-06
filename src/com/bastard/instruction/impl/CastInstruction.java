package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;


public class CastInstruction extends Instruction {

	public CastInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		System.out.println("\t\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "CastInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "CastInstruction[op=" + opcode.toString() + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(null, this);
	}

}
