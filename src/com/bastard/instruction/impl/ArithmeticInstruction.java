package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction that executes math operations on the stack.
 * @author Shawn Davies<sodxeh@gmail.com>
 *
 */
public class ArithmeticInstruction extends Instruction {

	public ArithmeticInstruction(int opcode) {
		super(opcode);
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
			return "ArithmeticInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "ArithmeticInstruction[op=" + opcode.toString() + "]";
	}

}
