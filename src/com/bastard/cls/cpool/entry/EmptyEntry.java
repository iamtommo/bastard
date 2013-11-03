package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class EmptyEntry extends ConstantPoolEntry {

	@Override
	public void read(ByteBuffer data) {
		
	}

	@Override
	public String toString() {
		return "EmptyEntry[]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

}
