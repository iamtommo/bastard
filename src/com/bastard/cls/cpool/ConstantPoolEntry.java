package com.bastard.cls.cpool;

import java.nio.ByteBuffer;

public abstract class ConstantPoolEntry {

	public ConstantPoolEntry() {
	}
	
	public abstract void read(ByteBuffer data);
	
	@Override
	public abstract String toString();
	
	/**
	 * Some entries such as longs and doubles reside in 2 indexes
	 * whereas most reside in just 1.
	 * @return The index incrementation for this entry.
	 */
	public abstract int getIndexIncrement();
	
}
