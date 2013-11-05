package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;
/**
 * An instruction which manipulates the stack
 * @author tommo
 *
 */
public class StackInstruction extends Instruction {

	public StackInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "StackInstruction[op=" + Opcode.valueOf(getOpcode() & 0xFF).toString() + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(pool, this);
	}

}
