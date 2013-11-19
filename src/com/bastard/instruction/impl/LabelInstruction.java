package com.bastard.instruction.impl;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.code.Node;
import com.bastard.instruction.Instruction;

public class LabelInstruction extends Instruction {

	private int startPc;

	private Map<String, Object> attributes = new HashMap<>();
	
	public LabelInstruction(ConstantPool pool, int startPc) {
		super(pool, -1);
		this.startPc = startPc;
	}
	
	public int getStartPc() {
		return startPc;
	}

	@Override
	public Instruction read(ConstantPool pool, ByteBuffer code) {
		return this;
	}

	@Override
	public Node toNode() {
		return null;
	}

	@Override
	public String toString() {
		return "LabelInstruction[startPc="+startPc+", isRoot="+((boolean) attributes.get("root") ? "true" : "false")+", isSink="+(attributes.get("sink") == null ? "false" : "true")+"]";
	}

	public void setAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	public Object getAttribute(String key) {
		return attributes.get(key);
	}
}
