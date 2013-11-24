package com.bastard.code.graph;

import java.util.LinkedHashMap;
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
	protected Map<K, V> vertices = new LinkedHashMap<K, V>();
	
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
		for (V vertex : vertices.values()) {
			vertex.print(indentations + 1);
		}
	}
	
	public int size() {
		return vertices.size();
	}
	
	@Override
	public String toString() {
		return "Graph[size=" + size() + "]";
	}
	
	public abstract void construct();
	
}
