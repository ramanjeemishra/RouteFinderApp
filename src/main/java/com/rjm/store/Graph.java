package com.rjm.store;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.EdgeNotFoundException;
import com.rjm.exception.VertexNotFoundException;

//FIXME ADT
public class Graph<T> implements GraphADT<T> {
	private final Map<T, List<T>> nodes;

	// TODO Prevent Others to call constructor
	Graph() {
		nodes = new LinkedHashMap<>();
	}

	// TODO Generalize naming
	@Override
	public boolean addNode(final T from, final T to) {
		nodes.putIfAbsent(from, new LinkedList<>());
		return nodes.get(from).add(to);
	}

	@Override
	public boolean addElementToNode(final List<T> node, final T element) {
		return node.add(element);
	}

	// TODO - READONLY LIST
	@Override
	public Map<T, List<T>> getNodes() {
		return nodes;
	}

	@Override
	public boolean isEmpty() {
		return (nodes == null) | nodes.isEmpty();
	}

	@Override
	public boolean hasVertex(final T v) {
		return nodes.get(v) != null;
	}

	@Override
	public List<T> getAdjacenyList(final T v) throws GraphNotInitializedException, VertexNotFoundException {
		if (isEmpty()) {
			throw new GraphNotInitializedException();
		}
		if (!hasVertex(v)) {
			throw new VertexNotFoundException(v);
		}
		return nodes.get(v);
	}

	@Override
	public T getEdge(final T v1, final T v2)
			throws GraphNotInitializedException, EdgeNotFoundException, VertexNotFoundException {
		final Optional<T> edge = getAdjacenyList(v1).stream().filter(v -> v.equals(v2)).findAny();
		if (edge.isPresent()) {
			return edge.get();
		}
		throw new EdgeNotFoundException(v1, v2);
	}
}
