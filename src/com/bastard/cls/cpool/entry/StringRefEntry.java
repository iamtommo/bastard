package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class StringRefEntry extends ConstantPoolEntry {

	/**
	 * The index in the constant pool containing the string
	 */
	private int stringIndex;

	@Override
	public void read(ByteBuffer data) {
		this.stringIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "StringRefEntry[" + stringIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

	public int getStringIndex() {
		return stringIndex;
	}

}
