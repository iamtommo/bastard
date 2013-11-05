package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction with no operands.
 * Opcodes: ICONST_0, ICONST_1, ICONST_3, ICONST_4, ICONST_5, LCONST_0, LCONST_1, FCONST_0, FCONST_1, FCONST_2,
 * DCONST_0, DCONST_1, IRETURN, LRETURN, FRETURN, DRETURN, ARETURN, RETURN.
 * @author Tommo
 *
 */
public class BasicInstruction extends Instruction {

	public BasicInstruction(ConstantPool pool, int opcode) {
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
			return "Instruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "Instruction[op=" + opcode.toString() + "]";
	}
	
	@Override
	public Node toNode() {
		return new Node(null, this);
	}

}
