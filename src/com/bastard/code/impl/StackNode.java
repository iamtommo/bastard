package com.bastard.code.impl;

import java.util.Arrays;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Opcode;
import com.bastard.instruction.impl.StackInstruction;

/**
 * Represents a {@link StackInstruction} as a node.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class StackNode extends Node {
	
	public StackNode(ConstantPool pool, StackInstruction instruction) {
		super(pool, instruction);
	}

	@Override
	public String code() {
		return "StackNode[type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).toString()+", children="+Arrays.toString(children.toArray())+"]";
	}
}
