package com.rjm.search;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public class DepthFirstPathSerach extends GraphSerachTemplate implements GraphSearch {
	public DepthFirstPathSerach(final GraphADT<Element> graph) {
		super(graph);
	}

	@Override
	protected Queue<Node> createFrontierCollection() {
		return new ArrayDeque<>();
	}

	@Override
	protected Node removeFront(final Queue<Node> frontier) {
		return ((Deque<Node>) frontier).pop();
	}

}
