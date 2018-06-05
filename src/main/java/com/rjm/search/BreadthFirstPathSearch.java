package com.rjm.search;

import java.util.ArrayDeque;
import java.util.Queue;

import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public class BreadthFirstPathSearch extends GraphSerachTemplate implements GraphSearch {

	public BreadthFirstPathSearch(final GraphADT<Element> graph) {
		super(graph);
	}

	@Override
	protected Node removeFront(final Queue<Node> frontier) {
		return ((ArrayDeque<Node>) frontier).poll();
	}

	@Override
	protected Queue<Node> createFrontierCollection() {
		return new ArrayDeque<>();
	}

}
