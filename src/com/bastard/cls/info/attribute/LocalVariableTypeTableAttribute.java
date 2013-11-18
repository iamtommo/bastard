package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class LocalVariableTypeTableAttribute extends AbstractAttribute {
	
	private int localVariableTypesLen;
	private LocalVariableInfo[] localVariableTypes;

	public LocalVariableTypeTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.localVariableTypesLen = data.getShort();
		this.localVariableTypes = new LocalVariableInfo[localVariableTypesLen];
		for (int i = 0; i < localVariableTypesLen; i++) {
			localVariableTypes[i] = new LocalVariableInfo().read(pool, data);
		}
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		for (int i = 0; i < localVariableTypesLen; i++) {
			localVariableTypes[i].print(indentations + 1);
		}
	}

	@Override
	public String toString() {
		return "LocalVariableTypeTableAttribute[len=" + localVariableTypesLen + "]";
	}

}
