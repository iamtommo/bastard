package com.bastard.code.graph;

import java.util.HashMap;
import java.util.Map;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.attribute.CodeAttribute;
import com.bastard.util.Indent;

/**
 * A graph with a list of vertices.
 * @author Shawn Davies<sodxeh@gmail.com>
 * @param <T> The type of vertices found within this graph.
 */
public abstract class Graph<K, V extends Vertex<K>> {

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
	protected Map<K, V> vertices = new HashMap<K, V>();
	
	public Graph(CodeAttribute code) {
		this.code = code;
		this.pool = code.getInstructionList().getConstantPool();
	}
	
	public void addVertex(K key, V vertex) {
		vertices.put(key, vertex);
	}
	
	@SuppressWarnings("unchecked")
	public void addEdge(Vertex<K> in, Vertex<K> out) {
		in.successors.add(out);
		out.predecessors.add(in);
		
		if (!vertices.containsKey(in.getKey())) {
			vertices.put(in.getKey(), (V) in);
		}
		
		if (!vertices.containsKey(out.getKey())) {
			vertices.put(out.getKey(), (V) out);
		}
	}
	
	public void deleteEdge(Vertex<K> in, Vertex<K> out) {
		in.successors.remove(out);
		out.predecessors.remove(in);
	}
	
	public V getVertex(K value) {
		return vertices.get(value);
	}
	
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
		System.out.println(Indent.$(indentations) + "{");
		for (V vertex : vertices.values()) {
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
