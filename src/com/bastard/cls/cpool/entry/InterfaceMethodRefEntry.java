package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class InterfaceMethodRefEntry extends ConstantPoolEntry {
	
	/**
	 * The index of the class reference index in the constant pool
	 */
	private int classRefIndex;
	
	/**
	 * The index of the name and type descriptor reference in the constant pool
	 */
	private int nameTypeRefIndex;

	@Override
	public void read(ByteBuffer data) {
		this.classRefIndex = data.getShort();
		this.nameTypeRefIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "InterfaceMethodRefEntry[classRefIndex=" + classRefIndex + ", nameTypeRefIndex=" + nameTypeRefIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

}
