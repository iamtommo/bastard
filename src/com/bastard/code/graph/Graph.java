package com.bastard.code.graph;

import java.util.ArrayList;
import java.util.List;

import com.bastard.cls.cpool.ConstantPool;
import com.bastard.cls.info.attribute.CodeAttribute;

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Graph[size="+vertices.size()+"]\n{\n");
		for (T vertex : vertices) {
			sb.append("\tVertex[index="+vertices.indexOf(vertex)+", degrees[in="+vertex.predecessors.size()+", out="+vertex.successors.size()+"]]\n\t{\n");
			sb.append("\t\tSuccessors[size="+vertex.successors.size()+"]\n\t\t{\n");
			
			for (Vertex successor : vertex.successors) {
				sb.append("\t\t\tVertex[degrees[in="+successor.predecessors.size()+", out="+successor.successors.size()+"]]\n");
			}
			sb.append("\t\t}\n");
			
			sb.append("\t\tPredecessors[size="+vertex.predecessors.size()+"]\n\t\t{\n");
			for (Vertex predecessor : vertex.predecessors) {
				sb.append("\t\t\tVertex[degrees[in="+predecessor.predecessors.size()+", out="+predecessor.successors.size()+"]]\n");
			}
			sb.append("\t\t}\n");
			
			sb.append("\t}\n");
		}
		sb.append("}\n");
		return sb.toString();
	}
	
	public abstract void construct();
}
