package com.rjm.model;

import static java.lang.String.format;

//FIXME - HOW ELEMENT IS DEFINED??? IS THIS EDGE OR VERTICES???
public class Element implements Comparable<Element> {

	private final String name;
	private final int cost;

	public Element(final String name, final int cost) {
		this.cost = cost;
		this.name = name;
	}

	public Element(final String name) {
		this(name, 0);
	}

	@Override
	public String toString() {
		return format("%s", name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Element other = (Element) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(final Element o) {
		return cost - o.cost;// FIXME - IS THIS CORRECT WAY???
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}

}
