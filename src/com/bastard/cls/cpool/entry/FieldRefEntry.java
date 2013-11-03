package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class FieldRefEntry extends ConstantPoolEntry {
	
	/**
	 * The class index in constant pool.
	 */
	private int classIndex;
	
	/**
	 * The NameType descriptor index in constant pool.
	 */
	private int nameTypeIndex;

	@Override
	public void read(ByteBuffer data) {
		classIndex = data.getShort();
		nameTypeIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "FieldRefEntry[" + classIndex + ", " + nameTypeIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

}
