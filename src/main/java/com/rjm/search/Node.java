package com.rjm.search;

import com.rjm.model.Element;

// LinkedList of Nodes as Path
// Frontier - Removing best item(priority queue) or adding new items
// Explored list - single set
class Node implements Comparable<Node> {
	Element state; // end of path
	String action; // action took to get there
	int depth;
	int cost;
	Node parent;

	Node(final Element state, final String action, final int depth, final int cost, final Node parent) {
		this.state = state;
		this.action = action;
		this.depth = depth;
		this.cost = cost;
		this.parent = parent;
	}

	@Override
	public int compareTo(final Node o) {
		return cost - o.cost;
	}
}