package com.rjm.search;

import java.util.PriorityQueue;
import java.util.Queue;

import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public class UniformCostPathSerach extends GraphSerachTemplate implements GraphSearch {
	public UniformCostPathSerach(final GraphADT<Element> graph) {
		super(graph);
	}

	@Override
	protected Queue<Node> createFrontierCollection() {
		return new PriorityQueue<>((a, b) -> a.compareTo(b));
	}

	@Override
	protected Node removeFront(final Queue<Node> frontier) {
		return frontier.poll();
	}

}
