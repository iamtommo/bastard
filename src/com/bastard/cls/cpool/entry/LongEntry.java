package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class LongEntry extends ConstantPoolEntry {
	
	/**
	 * The long value
	 */
	private long value;

	@Override
	public void read(ByteBuffer data) {
		this.value = data.getLong();
	}

	@Override
	public String toString() {
		return "LongRefEntry[value=" + value + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 2;
	}

	public long getValue() {
		return value;
	}

}
