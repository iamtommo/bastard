package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public interface Info {
	
	public Info read(ConstantPool pool, ByteBuffer data);
	
	public String toString();
	
}
