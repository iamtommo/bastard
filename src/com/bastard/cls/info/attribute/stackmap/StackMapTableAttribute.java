package com.bastard.cls.info.attribute.stackmap;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.attribute.AbstractAttribute;
import com.bastard.util.Indent;

public class StackMapTableAttribute extends AbstractAttribute {
	
	private int tableLength;
	private StackMapFrameInfo[] entries;

	public StackMapTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		tableLength = data.getShort();
		entries = new StackMapFrameInfo[tableLength];
		for (int i = 0; i < tableLength; i++) {
			StackMapFrameInfo frame = new StackMapFrameInfo().read(pool, data);
			entries[i] = frame;
		}
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}

	@Override
	public String toString() {
		return "StackMapTable[len=" + tableLength + "]";
	}

}
