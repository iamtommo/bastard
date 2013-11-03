package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class IntegerEntry extends ConstantPoolEntry {
	
	/**
	 * The int value
	 */
	private int value;

	@Override
	public void read(ByteBuffer data) {
		this.value = data.getInt();
	}

	@Override
	public String toString() {
		return "IntegerRefEntry[value=" + value+ "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

	public int getValue() {
		return value;
	}

}
