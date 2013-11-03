package com.bastard.cls.info;

public class ExceptionInfo {
	
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

}
