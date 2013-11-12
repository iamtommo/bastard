package com.bastard.instruction;

import java.lang.reflect.Constructor;
import java.nio.ByteBuffer;
import java.util.LinkedList;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

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
	
	private ConstantPool constantPool;
	
	public InstructionList() {
		super();
	}
	
	/**
	 * Fills this instruction list based on the code given.
	 * @param data The code.
	 */
	public void read(ConstantPool pool, ByteBuffer code) {
		this.code = code;
		this.constantPool = pool;
		int opcode;
		while ((code.hasRemaining()) && (opcode = code.get()) != -1) {
			Instruction insn;
			try {
				Opcode op = Opcode.valueOf(opcode & 0xFF);
				if (op != null) {
					Constructor<? extends Instruction> c = op.getInstructionClass().getConstructor(ConstantPool.class, int.class);
					insn = c.newInstance(pool, opcode).read(pool, code);
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
	
	public void print(int indentations) {
		for (int i = 0; i < size(); i++) {
			Instruction insn = get(i);
			System.out.print(Indent.$(indentations) + "" + i + ": ");
			insn.print(0);
		}
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

}
