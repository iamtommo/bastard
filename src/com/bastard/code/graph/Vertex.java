package com.bastard.code.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bastard.util.Indent;

/**
 * Represents a vertex on any graph.
 * @author Shawn Davies<sodxeh@gmail.com>
 *
 */
public abstract class Vertex<K> {

	/**
	 * The set of nodes that increase this vertex's in degree.
	 */
	protected Set<Vertex<K>> predecessors = new HashSet<Vertex<K>>();
	/**
	 * The set of nodes that increase this vertex's out degree.
	 */
	protected Set<Vertex<K>> successors = new HashSet<Vertex<K>>();
	/**
	 * A list of warning strings, to alert the reader of possible graph issues
	 * found when creating this vertex.
	 */
	protected List<String> warnings = new ArrayList<>();
	
	public abstract String toString();
	
	/**
	 * Does this vertex succeed the given vertex?
	 * @param vertex The vertex.
	 * @return {@code true} if this vertex succeeds the given vertex.
	 */
	public boolean succeeds(Vertex<K> vertex) {
		return predecessors.contains(vertex);
	}
	
	/**
	 * Does this vertex precede the given vertex?
	 * @param vertex The vertex.
	 * @return {@code true} if this vertex precedes the given vertex.
	 */
	public boolean precedes(Vertex<K> vertex) {
		return successors.contains(vertex);
	}
	
	public void print(int indentations) {
		System.out.println(Indent.$(indentations) + toString());
	}
	
	public void warn(String warning) {
		warnings.add(warning);
	}
	
	public abstract K getKey();
	
}
