package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

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
		return this;
	}
	
	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + "" + toString());
	}

	@Override
	public String toString() {
		return "ConstantValueAttribute[index=" + constantValueIndex + "]";
	}
	
	public int getConstantValueIndex() {
		return constantValueIndex;
	}

}
