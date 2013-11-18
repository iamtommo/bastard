package com.bastard.code.graph;

import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.code.Stack;

public abstract class Graph {

	protected Stack stack;
	protected CodeAttribute code;
	
	public Graph(CodeAttribute code, Stack stack) {
		this.stack = stack;
		this.code = code;
	}
	
	public abstract void construct();
}
