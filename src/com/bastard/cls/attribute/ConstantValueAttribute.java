package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public class ConstantValueAttribute extends AbstractAttribute {
	
	/**
	 * The index in the constant pool of the constant value
	 */
	private int constantValueIndex;

	public ConstantValueAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.constantValueIndex = data.getShort();
		System.out.println("\t\t\t" + toString());
		return this;
	}

	@Override
	public String toString() {
		return "ConstantValueAttribute[index=" + constantValueIndex + "]";
	}
	
	public int getConstantValueIndex() {
		return constantValueIndex;
	}

}
