package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction invoking a method.
 * Opcodes: INVOKESPECIAL, INVOKEVIRTUAL, INVOKESTATIC, INVOKEINTERFACE.
 * @author Tommo
 *
 */
public class MethodInstruction extends Instruction {

	/**
	 * The method reference index in the constant pool.
	 */
	private int methodIndex;

	public MethodInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public MethodInstruction read(ByteBuffer code) {
		if (getOpcode() == Opcode.INVOKEINTERFACE.getOpcode()) {
			methodIndex = code.getInt();
		/*|| getOpcode() == Opcode.INVOKEDYNAMIC)*/
		} else {
			methodIndex = code.getShort();
		}
		System.out.println("\t\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "MethodInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "MethodInstruction[op=" + opcode.toString() + ", methodIndex=" + methodIndex + "]";
	}

}
