package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.impl.MethodNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction invoking a method.
 * Opcodes: INVOKESPECIAL, INVOKEVIRTUAL, INVOKESTATIC, INVOKEINTERFACE.
 * @author Tommo
 *
 */
public class MethodInstruction extends Instruction {

	public int getMethodIndex() {
		return methodIndex;
	}

	/**
	 * The method reference index in the constant pool.
	 */
	private int methodIndex;

	public MethodInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public MethodInstruction read(ConstantPool pool, ByteBuffer code) {
		if (getOpcode() == Opcode.INVOKEINTERFACE.getOpcode()) {
			methodIndex = code.getInt();
		/*|| getOpcode() == Opcode.INVOKEDYNAMIC)*/
		} else {
			methodIndex = code.getShort();
		}
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
	
	@Override
	public Node toNode() {
		return new MethodNode(pool, this);
	}

}
