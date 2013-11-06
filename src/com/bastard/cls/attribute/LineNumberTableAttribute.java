package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.LineNumberInfo;

public class LineNumberTableAttribute extends AbstractAttribute {
	
	private int lineNumberTableLen;
	private LineNumberInfo[] lineNumberTable;

	public LineNumberTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.lineNumberTableLen = data.getShort();
		this.lineNumberTable = new LineNumberInfo[lineNumberTableLen];
		for (int i = 0; i < lineNumberTableLen; i++) {
			lineNumberTable[i] = new LineNumberInfo().read(pool, data);
		}
		System.out.println("\t\t\t\t" + toString());
		return this;
	}
	
	@Override
	public String toString() {
		return "LineNumberTableAttribute[tableLen=" + lineNumberTableLen + "]";
	}

}
