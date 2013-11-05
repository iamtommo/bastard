package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.impl.JumpNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction which can jump to another location.
 * Opcodes: GOTO, JSR, IF_ICOMPNE
 * @author Tommo
 *
 */
public class JumpInstruction extends Instruction {

	/**
	 * The location to jump to.
	 */
	private int jumpLocation;

	public JumpInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public JumpInstruction read(ConstantPool pool, ByteBuffer code) {
		if (getOpcode() == Opcode.GOTO_W.getOpcode() || getOpcode() == Opcode.JSR_W.getOpcode()) {
			jumpLocation = code.getInt() & 0xFFFF;
		} else {
			jumpLocation = code.getShort() & 0xFF;
		}
		System.out.println("\t\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "JumpInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		return "JumpInstruction[op=" + opcode.toString() + ", jumpLoc=" + jumpLocation + "]";
	}

	public int getJumpLocation() {
		return jumpLocation;
	}

	@Override
	public Node toNode() {
		return new JumpNode(this);
	}


}
