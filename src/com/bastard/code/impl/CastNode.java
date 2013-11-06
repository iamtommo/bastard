package com.bastard.code.impl;

import java.util.Arrays;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.SinglyEndedNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * Represents a cast, such as I2L.
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class CastNode extends SinglyEndedNode {

	public CastNode(ConstantPool pool, Node node, Node operand) {
		super(pool, node.getInstruction(), operand);
	}
	
	@Override
	public String code() {
		return "CastNode[type="+Opcode.valueOf(instruction.getOpcode() & 0xFF).toString()+", children="+Arrays.toString(children.toArray())+"]";
	}

}
