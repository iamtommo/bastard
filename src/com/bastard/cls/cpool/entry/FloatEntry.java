package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class FloatEntry extends ConstantPoolEntry {
	
	/**
	 * The float value
	 */
	private float value;

	@Override
	public void read(ByteBuffer data) {
		this.value = data.getFloat();
	}

	@Override
	public String toString() {
		return "FloatRefEntry[value=" + value + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

}
