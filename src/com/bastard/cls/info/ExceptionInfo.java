package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public class ExceptionInfo implements Info {
	
	/**
	 * Program counters
	 */
	private int startPc;
	private int endPc;
	private int handlerPc;
	
	/**
	 * Index in the constant pool of the exception handler.
	 */
	private int catchType;
	
	public ExceptionInfo(int startPc, int endPc, int handlerPc, int catchType) {
		this.startPc = startPc;
		this.endPc = endPc;
		this.handlerPc = handlerPc;
		this.catchType = catchType;
	}
	
	public Info read(ConstantPool pool, ByteBuffer data) {
		startPc = data.getShort();
		endPc = data.getShort();
		handlerPc = data.getShort();
		catchType = data.getShort();
		return this;
	}

	public int getStartPc() {
		return startPc;
	}

	public int getEndPc() {
		return endPc;
	}

	public int getHandlerPc() {
		return handlerPc;
	}

	public int getCatchType() {
		return catchType;
	}

}
