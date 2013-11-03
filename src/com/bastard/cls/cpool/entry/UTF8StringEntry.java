package com.bastard.cls.cpool.entry;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPoolEntry;

public class UTF8StringEntry extends ConstantPoolEntry {
	
	/**
	 * The string this entry holds.
	 */
	private String string;

	@Override
	public void read(ByteBuffer data) {
		int length = data.getShort();
		byte[] buf = new byte[length];
		data.get(buf);
		string = new String(buf);
	}
	
	/**
	 * 
	 * @return The string this entry refers to.
	 */
	public String getString() {
		return string;
	}

	@Override
	public String toString() {
		return "UTF8StringEntry[" + string + "]";
	}

	@Override
	public int getIndexIncrement() {
		return 1;
	}

}
