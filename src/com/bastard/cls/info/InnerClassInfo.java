package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class InnerClassInfo implements Info {
	
	/**
	 * The index in the constant pool of the inner class (ClassRefEntry)
	 */
	private int classRefIndex;
	
	/**
	 * The index in the constant pool of the outer class (ClassRefEntry)
	 */
	private int outerClassRefIndex;
	
	/**
	 * The index in the constant pool of the UTF8StringEntry representing this inner class name
	 */
	private int nameIndex;
	
	/**
	 * The access flags mask
	 */
	private int accessFlags;

	@Override
	public InnerClassInfo read(ConstantPool pool, ByteBuffer data) {
		this.classRefIndex = data.getShort();
		this.outerClassRefIndex = data.getShort();
		this.nameIndex = data.getShort();
		this.accessFlags = data.getShort();
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}
	
	@Override
	public String toString() {
		return "InnerClassInfo[classRefIndex=" + classRefIndex + ", outerClassRefIndex=" + outerClassRefIndex + ", nameIndex=" + nameIndex + 
				", accessFlags=" + Integer.toHexString(accessFlags) + "]";
	}

}
