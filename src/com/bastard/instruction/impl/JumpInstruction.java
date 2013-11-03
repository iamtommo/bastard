package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

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
	
	public JumpInstruction(int opcode) {
		super(opcode);
	}

	@Override
	public JumpInstruction read(ByteBuffer code) {
		if (getOpcode() == Opcode.GOTO_W.getOpcode() || getOpcode() == Opcode.JSR_W.getOpcode()) {
			jumpLocation = code.getInt();
		} else {
			jumpLocation = code.getShort();
		}
		System.out.println("\t\t\t" + toString());
		return this;
	}
	
	@Override
	public String toString() {
		return "JumpInstruction[op=" + Opcode.valueOf(getOpcode()).toString() + ", jumpLoc=" + jumpLocation + "]";
	}

}
