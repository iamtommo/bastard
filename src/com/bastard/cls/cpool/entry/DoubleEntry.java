package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class DoubleEntry extends ConstantPoolEntry {
	
	public long getValue() {
		return value;
	}

	/**
	 * The double value
	 */
	private long value;

	@Override
	public void read(ByteBuffer data) {
		this.value = data.getShort();
	}

	@Override
	public String toString() {
		return "DoubleRefEntry[value=" + value + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 2;
	}

}
