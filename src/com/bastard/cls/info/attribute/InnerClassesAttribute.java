package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class InnerClassesAttribute extends AbstractAttribute {
	
	private int innerClassesLen;
	private InnerClassInfo[] innerClasses;

	public InnerClassesAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.innerClassesLen = data.getShort();
		this.innerClasses = new InnerClassInfo[innerClassesLen];
		for (int i = 0; i < innerClassesLen; i++) {
			innerClasses[i] = new InnerClassInfo().read(pool, data);
		}
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		for (InnerClassInfo i : innerClasses) {
			i.print(indentations + 1);
		}
	}

	@Override
	public String toString() {
		return "InnerClassesAttribute[innerClasses=" + innerClassesLen + "]";
	}

}
