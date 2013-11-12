package com.bastard.cls.attribute;

import java.nio.ByteBuffer;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.AttributeInfo;
import com.bastard.cls.info.ExceptionInfo;
import com.bastard.code.Stack;
import com.bastard.code.graph.Graph;
import com.bastard.code.graph.JumpGraph;
import com.bastard.instruction.InstructionList;
import com.bastard.util.Indent;

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

	@SuppressWarnings("unchecked")
	private Class<Graph>[] graphs = new Class[] {
		JumpGraph.class
	};
	
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
		excTableLength = data.getShort();
		exceptionTable = new ExceptionInfo[excTableLength];
		for (int i = 0; i < exceptionTable.length; i++) {
			exceptionTable[i] = new ExceptionInfo().read(pool, data);
		}
		
		attrTableLength = data.getShort();
		attributes = new AttributeInfo[attrTableLength];
		for (int i = 0; i < attributes.length; i++) {
			AttributeInfo ai = new AttributeInfo().read(pool, data);
			attributes[i] = ai;
		}
		
		stack = new Stack(instructionList);
		
		for (Class<Graph> cls : graphs) {
			try {
				Graph graph = (Graph) cls.getConstructor(CodeAttribute.class, Stack.class).newInstance(this, stack);
				
				graph.construct();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this;
	}
	
	@Override
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + "" + toString());
		
		for (int i = 0; i < exceptionTable.length; i++) {
			exceptionTable[i].print(indentations + 1);
		}
		
		for (int i = 0; i < attributes.length; i++) {
			attributes[i].print(indentations + 1);
		}
		
		instructionList.print(indentations + 1);
		
		stack.print(indentations + 1);
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

	public int getMaxStack() {
		return maxStack;
	}

	public int getMaxLocals() {
		return maxLocals;
	}

	public int getCodeLength() {
		return codeLength;
	}

	public ByteBuffer getCode() {
		return code;
	}

	public int getExcTableLength() {
		return excTableLength;
	}

	public ExceptionInfo[] getExceptionTable() {
		return exceptionTable;
	}

	public int getAttrTableLength() {
		return attrTableLength;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

	public InstructionList getInstructionList() {
		return instructionList;
	}

}
