package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.util.Indent;

/**
 * An enclosing method is existent in only local or anonymous classes
 * @author tommo
 *
 */
public class EnclosingMethodAttribute extends AbstractAttribute {
	
	/**
	 * Index in the constant pool of the ClassRefEntry
	 */
	private int classIndex;
	
	/**
	 * Index in the constant pool of the MethodRefEntry
	 */
	private int methodIndex;

	public EnclosingMethodAttribute(int nameIndex, int length) {
		super(nameIndex, length);
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		this.classIndex = data.getShort();
		this.methodIndex = data.getShort();
		return this;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}

	@Override
	public String toString() {
		return "EnclosingMethodAttribute[classIndex=" + classIndex + ", methodIndex=" + methodIndex + "]";
	}

}
