package com.bastard.code.impl;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.PushInstruction;

/**
 * Represents a node pushing a value directly onto the stack (BIPUSH AND SIPUSH).
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class PushNode extends Node {

	private int value;
	
	public PushNode(ConstantPool pool, PushInstruction instruction) {
		super(pool, instruction);
		this.value = instruction.getValue();
	}

	public int getValue() {
		return value;
	}

	@Override
	public String code() {
		return "PushNode[value="+value+", size="+Opcode.valueOf(instruction.getOpcode() & 0xFF).toString()+"]";
	}
}
