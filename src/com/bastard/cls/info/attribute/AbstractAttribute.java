package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public abstract class AbstractAttribute {
	
	public AbstractAttribute() {
	}
	
	public abstract AbstractAttribute read(ConstantPool pool, ByteBuffer data);

}
