package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.LineNumberInfo;
import com.bastard.util.Indent;

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
		return this;
	}
	
	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		for (int i = 0; i < lineNumberTableLen; i++) {
			lineNumberTable[i].print(indentations + 1);
		}
	}
	
	public LineNumberInfo[] getTable() {
		return lineNumberTable;
	}
	
	@Override
	public String toString() {
		return "LineNumberTableAttribute[tableLen=" + lineNumberTableLen + "]";
	}

}
