package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction pushing a variable onto the stack.
 * <p>
 * Opcodes: SIPUSH, BIPUSH
 * @author Tommo
 *
 */
public class PushInstruction extends Instruction {
	
	public int getValue() {
		return value;
	}

	/**
	 * The value this instruction pushes to the stack.
	 */
	private int value;
	
	public PushInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public PushInstruction read(ConstantPool pool, ByteBuffer code) {
		if (getOpcode() == Opcode.SIPUSH.getOpcode()) {
			this.value = code.getShort();
		} else {
			this.value = code.get();
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "VariableInstruction[op=" + Opcode.valueOf(getOpcode() & 0xFF).toString() + ", value=" + value + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(pool, this);
	}

}
