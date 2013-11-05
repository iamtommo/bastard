package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;
/**
 * An instruction which manipulates the stack
 * @author tommo
 *
 */
public class StackInstruction extends Instruction {

	public StackInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public String toString() {
		return "StackInstruction[op=" + Opcode.valueOf(getOpcode() & 0xFF).toString() + "]";
	}

}
