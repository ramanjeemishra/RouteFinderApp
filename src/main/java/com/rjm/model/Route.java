package com.rjm.model;

import static java.lang.String.format;

public class Route {

	private final String from;
	private final String to;
	private final int cost;

	public Route(final String from, final String to, final int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return format("Route [From=%s , To=%s , Cost=%d ]", from, to, cost);
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public int getCost() {
		return cost;
	}

}
