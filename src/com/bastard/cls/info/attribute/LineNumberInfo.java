package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.Info;
import com.bastard.util.Indent;

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
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + "" + toString());
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
