package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public class LineNumberInfo implements Info {
	
	/**
	 * Start program counter for this line number
	 */
	private int startPc;
	
	/**
	 * The line number in the original source file
	 */
	private int lineNumber;

	@Override
	public LineNumberInfo read(ConstantPool pool, ByteBuffer data) {
		this.startPc = data.getShort();
		this.lineNumber = data.getShort();
		return this;
	}
	
	@Override
	public String toString() {
		return "LineNumberInfo[startPc=" + startPc + ", lineNum=" + lineNumber + "]";
	}

	public int getStartPc() {
		return startPc;
	}

	public int getLineNumber() {
		return lineNumber;
	}

}
