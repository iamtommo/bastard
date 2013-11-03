package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction pushing a variable onto the stack.
 * <p>
 * Opcodes: SIPUSH, BIPUSH
 * @author Tommo
 *
 */
public class VariableInstruction extends Instruction {
	
	/**
	 * The value this instruction pushes to the stack.
	 */
	private int value;
	
	public VariableInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public VariableInstruction read(ByteBuffer code) {
		value = code.getShort();
		System.out.println("\t\t\t" + toString());
		return this;
	}
	
	@Override
	public String toString() {
		return "VariableInstruction[op=" + Opcode.valueOf(getOpcode()).toString() + ", value=" + value + "]";
	}

}
