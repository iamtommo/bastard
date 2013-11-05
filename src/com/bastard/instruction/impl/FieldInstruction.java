package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.impl.FieldNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction concerning a field.
 * Opcodes: GETSTATIC, GETFIELD, PUTSTATIC, PUTFIELD.
 * @author Tommo
 *
 */
public class FieldInstruction extends Instruction {
	
	/**
	 * The field index in the constant pool this instruction concerns.
	 */
	private int fieldIndex;
	
	public FieldInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public FieldInstruction read(ConstantPool pool, ByteBuffer code) {
		fieldIndex = code.getShort();
		System.out.println("\t\t\t\t" + toString());
		return this;
	}
	
	public int getFieldIndex() {
		return fieldIndex;
	}
	 
	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "FieldInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "FieldInstruction[op=" + opcode.toString() + ", fieldIndex=" + fieldIndex + "]";
	}
	
	@Override
	public Node toNode() {
		return new FieldNode(null, this);
	}

}
