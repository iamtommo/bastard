package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class MethodRefEntry extends ConstantPoolEntry {
	
	/**
	 * The {@link ClassRefEntry} at the index in the constant pool.
	 */
	private int classRefIndex;
	
	/**
	 * The {@link NameTypeRefEntry} at the index in the constant pool.
	 */
	private int nameTypeRefIndex;

	@Override
	public void read(ByteBuffer data) {
		classRefIndex = data.getShort();
		nameTypeRefIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "MethodRefEntry[" + classRefIndex + ", " + nameTypeRefIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

	public int getClassRefIndex() {
		return classRefIndex;
	}

	public int getNameTypeRefIndex() {
		return nameTypeRefIndex;
	}

}
