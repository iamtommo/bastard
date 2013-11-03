package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class ClassRefEntry extends ConstantPoolEntry {
	
	/**
	 * The index of the class name string in the constant pool.
	 */
	private int stringIndex;

	public ClassRefEntry() {
	}
	
	public void read(ByteBuffer data) {
		stringIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "ClassRefEntry[" + stringIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}
	
	public int getStringIndex() {
		return stringIndex;
	}
	
}
