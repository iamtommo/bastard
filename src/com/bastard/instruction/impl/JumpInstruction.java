package com.bastard.instruction.impl;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.code.graph.block.CodeBlock;
import com.bastard.code.impl.JumpNode;
import com.bastard.instruction.Instruction;
import com.bastard.instruction.Opcode;

/**
 * An instruction which can jump to another location.
 * Opcodes: GOTO, JSR, IF_ICOMPNE
 * @author Tommo
 * @author Shawn Davies<sodxeh@gmail.com>
 */
public class JumpInstruction extends Instruction {

	/**
	 * The destination in the code array of this instruction.
	 */
	private int dst;
	/**
	 * The block which holds the target instruction.
	 */
	private CodeBlock targetBlock;
	/**
	 * The destination within the target block.
	 */
	private int targetDst;

	public JumpInstruction(ConstantPool pool, int opcode) {
		super(pool, opcode);
	}

	@Override
	public JumpInstruction read(ConstantPool pool, ByteBuffer code) {
		if (getOpcode() == Opcode.GOTO_W.getOpcode() || getOpcode() == Opcode.JSR_W.getOpcode()) {
			dst = code.getInt() & 0xFFFF;
		} else {
			dst = code.getShort() & 0xFF;
		}
		return this;
	}

	@Override
	public String toString() {
		Opcode opcode = Opcode.valueOf(getOpcode() & 0xFF);
		if (opcode == null) {
			return "JumpInstruction[op="+(getOpcode() & 0xFF)+", null]";
		}
		if (targetBlock != null) {
			return "JumpInstruction[op=" + opcode.toString() + ", targetBlock="+targetBlock.getTag()+", destinations=[code="+dst+", target="+targetDst+"]]";
		}
		return "JumpInstruction[op=" + opcode.toString() + ", dst=" + dst + "]";
	}

	public int getDestination() {
		return this.dst;
	}
	
	public int getTargetDestination() {
		return this.targetDst;
	}

	@Override
	public Node toNode() {
		return new JumpNode(this);
	}

	public void setDestination(int dst) {
		this.dst = dst;
	}

	public CodeBlock getTargetBlock() {
		return targetBlock;
	}

	public void setTargetBlock(CodeBlock block) {
		this.targetBlock = block;
	}

	public void setTargetDestination(int dst) {
		this.targetDst = dst;
	}


}
