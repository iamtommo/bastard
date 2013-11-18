package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class LocalVariableTableAttribute extends AbstractAttribute {
	
	private int localVariablesLen;
	private LocalVariableInfo[] localVariables;

	public LocalVariableTableAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.localVariablesLen = data.getShort();
		this.localVariables = new LocalVariableInfo[localVariablesLen];
		for (int i = 0; i < localVariablesLen; i++) {
			localVariables[i] = new LocalVariableInfo().read(pool, data);
		}
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		for (int i = 0; i < localVariablesLen; i++) {
			localVariables[i].print(indentations + 1);
		}
	}

	@Override
	public String toString() {
		return "LocalVariableTableAttribute[len=" + localVariablesLen + "]";
	}

	public int getLocalVariablesLen() {
		return localVariablesLen;
	}

	public LocalVariableInfo[] getLocalVariables() {
		return localVariables;
	}

}
