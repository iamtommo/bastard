package com.bastard.code.impl;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class LabelNode extends Node {

	private int lineNumber;
	private int startPc;

	private boolean root;
	
	public LabelNode(ConstantPool pool, Instruction instruction, int startPc, int lineNumber) {
		super(pool, instruction);
		this.startPc = startPc;
		this.lineNumber = lineNumber;
	}

	@Override
	public String code() {
		return "LabelNode[startPc="+startPc+", isRoot="+root+", line="+lineNumber+"]";
	}

	public void setRoot(boolean root) {
		this.root = root;
	}
	
	public boolean isRoot() {
		return root;
	}
}
