package com.rjm.store;

import java.util.List;

import com.rjm.exception.GraphNotInitializedException;

public class GraphBuilder<T> {
	private final GraphADT<T> graph;

	public GraphBuilder() {
		super();
		graph = new Graph<>();
	}

	public static <T> GraphBuilder<T> createGraph() {
		return new GraphBuilder<>();
	}

	// FIXME - NOT OPTIMAL
	public GraphBuilder<T> addElement(final List<T> node, final T element) throws GraphNotInitializedException {
		if (graph.isEmpty()) {
			throw new GraphNotInitializedException();
		}
		graph.addElementToNode(node, element);
		return this;
	}

	public GraphBuilder<T> addRoute(final List<T> vertices) {
		graph.addNode(vertices.get(0), vertices.get(1));
		return this;
	}

	public GraphADT<T> builtGraph() {
		return this.graph;
	}

	public GraphBuilder<T> addRoute(final T from, final T to) {
		graph.addNode(from, to);
		return this;
	}

}
