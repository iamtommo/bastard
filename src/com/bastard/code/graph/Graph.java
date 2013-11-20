package com.bastard.code.graph;

import java.util.ArrayList;
import java.util.List;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.util.Indent;

/**
 * A graph with a list of vertices.
 * @author Shawn Davies<sodxeh@gmail.com>
 * @param <T> The type of vertices found within this graph.
 */
public abstract class Graph<T extends Vertex> {

	/**
	 * A link to the raw code attributes, which are used to construct
	 * the graph.
	 */
	protected CodeAttribute code;
	/**
	 * The constant pool.
	 */
	protected ConstantPool pool;
	
	/**
	 * The vertices within this graph.
	 */
	protected List<T> vertices = new ArrayList<>();
	
	public Graph(CodeAttribute code) {
		this.code = code;
		this.pool = code.getInstructionList().getConstantPool();
	}
	
	public boolean addVertex(T vertex) {
		return vertices.add(vertex);
	}
	
	public void addEdge(Vertex in, Vertex out) {
		in.successors.add(out);
		out.predecessors.add(in);
	}
	
	public void deleteEdge(Vertex in, Vertex out) {
		in.successors.remove(out);
		out.predecessors.remove(in);
	}
	
	public T getVertex(int index) {
		return vertices.get(index);
	}
	
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		System.out.println(Indent.$(indentations) + "{");
		for (T vertex : vertices) {
			vertex.print(indentations + 1);
		}
		System.out.println(Indent.$(indentations) + "}");
	}
	
	@Override
	public String toString() {
		return "Graph[size=" + vertices.size() + "]";
	}
	
	public abstract void construct();
	
}
