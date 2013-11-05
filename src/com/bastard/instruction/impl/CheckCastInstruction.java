package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class CheckCastInstruction extends Instruction {
	
	/**
	 * The index in the constant pool of the class reference this instruction is checking a cast for
	 */
	private int classRefIndex;

	public CheckCastInstruction(ConstantPool pool, int opcode) {
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
		return "CheckCastInstruction[index=" + classRefIndex + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(null, this);
	}

}
