package com.bastard.cls.info;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;

public class ExceptionInfo implements Info {
	
	/**
	 * Starting program counter (inclusive)
	 */
	private int startPc;
	
	/**
	 * End program counter (exclusive)
	 */
	private int endPc;
	
	/**
	 * Start of the exception handler
	 */
	private int handlerPc;
	
	/**
	 * Index in the constant pool of the exception handler (ClassRefEntry)
	 */
	private int catchTypeIndex;
	
	public ExceptionInfo() {
	}
	
	@Override
	public ExceptionInfo read(ConstantPool pool, ByteBuffer data) {
		startPc = data.getShort();
		endPc = data.getShort();
		handlerPc = data.getShort();
		catchTypeIndex = data.getShort();
		return this;
	}
	
	@Override
	public String toString() {
		return "ExceptionInfo[startPc=" + startPc + ", endPc=" + endPc + ", handlerPc=" + handlerPc + ", catchTypeIndex=" + catchTypeIndex + "]";
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

	public int getCatchTypeIndex() {
		return catchTypeIndex;
	}

}
