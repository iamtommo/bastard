package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

public class LocalVariableInfo implements Info {
	
	/**
	 * The start pc where the local variable first has a value
	 */
	private int startPc;
	
	/**
	 * The length between startPc+length where the local variable has a value
	 */
	private int length;
	
	/**
	 * Index in the constant pool of the UTF8StringEntry representing the name
	 */
	private int nameIndex;
	
	/**
	 * Index in the constant pool of the UTF8StringEntry representing the field descriptor
	 */
	private int descriptorIndex;
	
	/**
	 * The index of this local variable in the current stack frame
	 * <p>
	 * Note: if type is of double or long, occupies index and index + 1
	 */
	private int index;

	@Override
	public LocalVariableInfo read(ConstantPool pool, ByteBuffer data) {
		this.startPc = data.getShort();
		this.length = data.getShort();
		this.nameIndex = data.getShort();
		this.descriptorIndex = data.getShort();
		this.index = data.getShort();
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}
	
	@Override
	public String toString() {
		return "LocalVariableInfo[startPc=" + startPc + ", len=" + length + ", nameIndex=" + nameIndex + ", descriptorIndex=" + descriptorIndex 
				+ ", index=" + index + "]";
	}

}
