package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class MethodInfo implements Info {
	
	private int accessFlags;
	private int nameIndex;
	private int descriptorIndex;
	private int attributesCount;
	private AttributeInfo[] attributes;

	public MethodInfo() {
	}

	@Override
	public MethodInfo read(ConstantPool pool, ByteBuffer data) {
		accessFlags = data.getShort();
		nameIndex = data.getShort();
		descriptorIndex = data.getShort();
		attributesCount = data.getShort();
		attributes = new AttributeInfo[attributesCount];
		for (int i = 0; i < attributesCount; i++) {
			AttributeInfo ai = new AttributeInfo().read(pool, data);
			attributes[i] = ai;
		}
		return this;
	}
	
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + "" + toString());
		for (int i = 0; i < attributesCount; i++) {
			attributes[i].print(indentations + 1);
		}
	}
	
	@Override
	public String toString() {
		return "MethodInfo[accessFlags=" + accessFlags + ", nameIdx=" + nameIndex + ", descIndex=" + descriptorIndex
				+ ", attribCount=" + attributesCount +  "]";
	}

	public int getAccessFlags() {
		return accessFlags;
	}

	public int getNameIndex() {
		return nameIndex;
	}

	public int getDescriptorIndex() {
		return descriptorIndex;
	}

	public int getAttributesCount() {
		return attributesCount;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}
	
}
