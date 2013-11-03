package com.bastard.instruction;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.LinkedList;

/**
 * The bytecode instruction list.
 * @author Tommo
 *
 */
@SuppressWarnings("serial")
public class InstructionList extends LinkedList<Instruction> {
	
	/**
	 * The original code byte array.
	 */
	@SuppressWarnings("unused")
	private ByteBuffer code;
	
	public InstructionList() {
		super();
	}
	
	/**
	 * Fills this instruction list based on the code given.
	 * @param data The code.
	 */
	public void read(ByteBuffer code) {
		this.code = code;
		int opcode;
		while ((code.hasRemaining()) && (opcode = code.get()) != -1) {
			Instruction insn;
			try {
				Opcode op = Opcode.valueOf(opcode & 0xFF);
				if (op != null) {
					Constructor<? extends Instruction> c = op.getInstructionClass().getConstructor(int.class);
					insn = c.newInstance(opcode).read(code);
					add(insn);
				} else {
					System.out.println("Unknown opcode: " + Integer.toHexString(opcode & 0xFF));
					System.exit(-1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
