package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class SourceFileAttribute extends AbstractAttribute {
	
	/**
	 * Index in the constant pool of the source file string entry
	 */
	private int sourceFileIndex;

	public SourceFileAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		sourceFileIndex = data.getShort();
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}

	@Override
	public String toString() {
		return "SourceFileAttribute[srcIndex=" + sourceFileIndex + "]";
	}

}
