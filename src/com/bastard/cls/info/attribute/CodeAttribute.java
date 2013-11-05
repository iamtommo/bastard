package com.bastard.cls.info.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.AttributeInfo;
import com.bastard.cls.info.ExceptionInfo;
import com.bastard.code.Stack;
import com.bastard.instruction.InstructionList;

public class CodeAttribute extends AbstractAttribute {
	
	public static final String ENTRY_STRING = "Code";
	
	private int nameIndex;
	private int length;
	private int maxStack;
	private int maxLocals;
	private int codeLength;
	private ByteBuffer code;
	private int excTableLength;
	private ExceptionInfo[] exceptionTable;
	private int count;
	private AttributeInfo[] attributes;
	private InstructionList instructionList = new InstructionList();

	private Stack stack;
	
	public CodeAttribute(int nameIndex, int length) {
		this.nameIndex = nameIndex;
		this.length = length;
	}

	@Override
	public AbstractAttribute read(ConstantPool pool, ByteBuffer data) {
		maxStack = data.getShort();
		maxLocals = data.getShort();
		codeLength = data.getInt();
		byte[] b = new byte[codeLength];
		data.get(b);
		code = ByteBuffer.wrap(b);
		instructionList.read(pool, code);
		stack = new Stack(instructionList);
		stack.print();
		excTableLength = data.getShort();
		//TODO: Implement exception loading in actual ExceptionInfo file...
		exceptionTable = new ExceptionInfo[excTableLength];
		for (int i = 0; i < exceptionTable.length; i++) {
			int startPc = data.getShort();
			int endPc = data.getShort();
			int handlerPc = data.getShort();
			int catchType = data.getShort();
			exceptionTable[i] = new ExceptionInfo(startPc, endPc, handlerPc, catchType);
		}
		count = data.getShort();
		attributes = new AttributeInfo[count];
		for (int i = 0; i < attributes.length; i++) {
			AttributeInfo ai = new AttributeInfo().read(pool, data);
			attributes[i] = ai;
		}
		System.out.println("\t\t\t" + toString());
		return this;
	}
	
	@Override
	public String toString() {
		return "CodeAttribute[nameIdx=" + nameIndex + ", len=" + length + ", maxStack=" + maxStack + ", maxLocals=" +
				maxLocals + ", codeLen=" + codeLength + ", exceptionTableLen=" + excTableLength
					+ ", attrCount=" + count + "]";
	}
	
	public Stack getStack() {
		return stack;
	}

}
