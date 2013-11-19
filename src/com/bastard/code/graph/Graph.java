package com.bastard.code.graph;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.attribute.CodeAttribute;

public abstract class Graph {

	protected CodeAttribute code;
	protected ConstantPool pool;
	
	public Graph(CodeAttribute code) {
		this.code = code;
		this.pool = code.getInstructionList().getConstantPool();
	}
	
	public abstract void construct();
}
