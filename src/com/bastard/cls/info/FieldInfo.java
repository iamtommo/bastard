package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public class FieldInfo implements Info {
	
	public static final int ACCESS_PUBLIC = 0x0001;
	public static final int ACCESS_PRIVATE = 0x0002;
	public static final int ACCESS_PROTECTED = 0x0004;
	public static final int ACCESS_STATIC = 0x0008;
	public static final int ACCESS_FINAL = 0x0010;
	public static final int ACCESS_VOLATILE = 0x0040;
	public static final int ACCESS_TRANSIENT = 0x0080;
	
	private int accessFlags;
	private int nameIndex;
	private int descriptorIndex;
	private int attributesCount;
	private AttributeInfo[] attributes;

	public FieldInfo() {
	}
	
	public FieldInfo read(ConstantPool pool, ByteBuffer data) {
		accessFlags = data.getShort();
		nameIndex = data.getShort();
		descriptorIndex = data.getShort();
		attributesCount = data.getShort();
		attributes = new AttributeInfo[attributesCount];
		System.out.println("\t\t" + toString());
		for (int i = 0; i < attributesCount; i++) {
			AttributeInfo ai = new AttributeInfo().read(pool, data);
			attributes[i] = ai;
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Field[accessFlags=" + accessFlags + ", nameIdx=" + nameIndex + ", descIndex=" + descriptorIndex
				+ ", attribCount=" + attributesCount + "]";
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
