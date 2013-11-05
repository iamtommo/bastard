package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class NameTypeRefEntry extends ConstantPoolEntry {
	
	private int nameIndex;
	private int descriptorIndex;

	@Override
	public void read(ByteBuffer data) {
		nameIndex = data.getShort();
		descriptorIndex = data.getShort();
	}

	@Override
	public String toString() {
		return "NameTypeRefEntry[" + nameIndex + ", " + descriptorIndex + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

}
