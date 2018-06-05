package com.rjm.store;

import java.util.List;
import java.util.Map;

import com.rjm.exception.EdgeNotFoundException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.VertexNotFoundException;

public interface GraphADT<T> {

	boolean addNode(T v1, T v2);

	boolean addElementToNode(List<T> node, T element);

	Map<T, List<T>> getNodes();

	boolean isEmpty();

	boolean hasVertex(T v);

	List<T> getAdjacenyList(T v) throws GraphNotInitializedException, VertexNotFoundException;

	T getEdge(T v1, T v2) throws GraphNotInitializedException, EdgeNotFoundException, VertexNotFoundException;

}
