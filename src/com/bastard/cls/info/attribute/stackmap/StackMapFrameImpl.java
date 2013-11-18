package com.bastard.cls.info.attribute.stackmap;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.Info;
import com.bastard.util.Indent;

public abstract class StackMapFrameImpl implements Info {
	
	private int frameType;
		
	public StackMapFrameImpl(int frameType) {
		this.frameType = frameType;
	}
		
	public StackMapFrameImpl read(ConstantPool pool, ByteBuffer data) {
		return this;
	}
		
	public int getFrameType() {
		return frameType;
	}

	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}
	
	public abstract String toString();
	
}