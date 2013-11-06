package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.AttributeInfo;
import com.bastard.cls.info.ExceptionInfo;
import com.bastard.code.Stack;
import com.bastard.instruction.InstructionList;

public class CodeAttribute extends AbstractAttribute {

	private int maxStack;
	private int maxLocals;
	private int codeLength;
	private ByteBuffer code;
	private int excTableLength;
	private ExceptionInfo[] exceptionTable;
	private int attrTableLength;
	private AttributeInfo[] attributes;
	private InstructionList instructionList = new InstructionList();

	private Stack stack;
	
	public CodeAttribute(int nameIndex, int length) {
		super(nameIndex, length);
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
		exceptionTable = new ExceptionInfo[excTableLength];
		for (int i = 0; i < exceptionTable.length; i++) {
			exceptionTable[i] = new ExceptionInfo().read(pool, data);
			System.out.println("\t\t\t\t" + exceptionTable[i].toString());
		}
		
		attrTableLength = data.getShort();
		attributes = new AttributeInfo[attrTableLength];
		for (int i = 0; i < attributes.length; i++) {
			AttributeInfo ai = new AttributeInfo().read(pool, data);
			attributes[i] = ai;
		}
		System.out.println("\t\t\t" + toString());
		return this;
	}
	
	@Override
	public String toString() {
		return "CodeAttribute[nameIdx=" + getNameIndex() + ", len=" + getLength() + ", maxStack=" + maxStack + ", maxLocals=" +
				maxLocals + ", codeLen=" + codeLength + ", exceptionTableLen=" + excTableLength
					+ ", attributeTableLen=" + attrTableLength + "]";
	}
	
	public Stack getStack() {
		return stack;
	}

}
