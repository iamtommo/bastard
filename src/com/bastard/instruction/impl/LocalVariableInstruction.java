package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.impl.LocalVariableNode;
import com.bastard.instruction.Instruction;

public class LocalVariableInstruction extends Instruction {

	/**
	 * The index of the local variable if applicable
	 */
	private int index;

	public LocalVariableInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		this.index = code.get();
		return this;
	}

	@Override
	public String toString() {
		return "LocalVariableInstruction[index="+index+"]";
	}
	
	public int getIndex() {
		return index;
	}

	@Override
	public Node toNode() {
		return new LocalVariableNode(this);
	}
}
