package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * wide is placed in front of an instruction that accesses a local variable. 
 * You use it to extend the range of local variables available to the instruction from 8 bits (i.e. 0-255) to 16 bits (i.e. 0-65535).
 * In addition, for iinc, it increases the increment range. wide is used in conjunction with one of the 
 * following opcodes: aload, dload, iload, fload, lload, astore, dstore, istore, fstore, lstore, iinc and ret. 
 * @author tommo
 *
 */
public class WideInstruction extends Instruction {
	
	/**
	 * The opcode of the instruction this wide instruciton is preceding
	 */
	private int executeOpcode;
	
	/**
	 * The index of the _load or iinc instruction
	 */
	private int index;
	
	/**
	 * The signed 16bit short constant for the iinc instruction
	 */
	private int inc;

	public WideInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		executeOpcode = code.get();
		index = code.getShort();
		if (executeOpcode == Opcode.IINC.getOpcode()) {
			inc = code.getShort();
		}
		return this;
	}

	@Override
	public Node toNode() {
		return new Node(pool, this);
	}

	@Override
	public String toString() {
		return "WideInstruction[op=" + Opcode.valueOf(executeOpcode) + ", index=" + index + ", inc=" + inc + "]";
	}

}
