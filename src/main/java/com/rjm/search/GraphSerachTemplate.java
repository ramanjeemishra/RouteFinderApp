package com.rjm.search;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.BiPredicate;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.model.Element;
import com.rjm.store.GraphADT;

abstract class GraphSerachTemplate {

	private final GraphADT<Element> graph;

	protected GraphSerachTemplate(final GraphADT<Element> graph) {
		this.graph = graph;
	}

	public int findDistance(final String from, final String to,
			final BiPredicate<Integer, Integer> resultAcceptableCondition,
			final BiPredicate<Integer, Integer> stopCondition)
			throws DestinationNotProvidedException, PathNotFoundException {
		if (to == null) {
			throw new DestinationNotProvidedException();
		}
		return graphSearch(from, to, resultAcceptableCondition, stopCondition, false).size();
	}

	public int lengthOfShortestPath(final String from, final String to)
			throws DestinationNotProvidedException, PathNotFoundException {
		if (to == null) {
			throw new DestinationNotProvidedException();
		}
		final List<Node> result = graphSearch(from, to, (i, j) -> true, (i, j) -> false, true);
		return result.get(0).cost;
	}

	public int countTrips(final String from, final String to,
			final BiPredicate<Integer, Integer> resultAcceptableCondition,
			final BiPredicate<Integer, Integer> searchStopCondition) throws PathNotFoundException {
		final List<Node> paths = graphSearch(from, to, resultAcceptableCondition, searchStopCondition, false);
		return paths.size();
	}

	//TODO - SPLIT RESPONSIBILITY
	private List<Node> graphSearch(final String from, final String to,
			final BiPredicate<Integer, Integer> resultAcceptableCondition,
			final BiPredicate<Integer, Integer> searchStopCondition, final boolean firstRouteOnly) {
		final Element startNode = new Element(from, 0);
		final Element targetNode = new Element(to, 0);
		final Queue<Node> frontier = makeNode(startNode);
		final Set<Element> explored = new HashSet<>();
		// All possible paths, depending on search decorations
		final List<Node> path = new ArrayList<>(); 
		System.out.print(String.format("\nFinding route for - [%s] -> [%s].\n", from, to));

		int routeFound = 0;
		Node frontNode = null;

		while (!frontier.isEmpty()) {
			frontNode = removeFront(frontier);
			if (targetNode.equals(frontNode.state)) {
				explored.clear();
				if ((frontNode.depth > 0) && resultAcceptableCondition.test(frontNode.depth, frontNode.cost)) {
					System.out.printf("Path-%d %s, cost %d, depth %d.\n", routeFound, frontNode.action, frontNode.cost,
							frontNode.depth);
					routeFound++;
					path.add(frontNode);
					if (firstRouteOnly) {
						return path;
					}
				}
			}
			frontier.addAll(expand(frontNode, explored));
			if (!shouldContinueExploration(frontier, searchStopCondition)) {
				System.out.println("Constraints cant be met any more...terminating search...");
				break;
			}
		}
		return path;
	}

	private boolean shouldContinueExploration(final Queue<Node> frontier,
			final BiPredicate<Integer, Integer> searchStopCondition) {
		boolean anyNodeCanMeetConstraint = false;
		for (final Node node : frontier) {
			if (searchStopCondition.negate().test(node.depth, node.cost)) {
				return anyNodeCanMeetConstraint = true;
			}
		}
		return anyNodeCanMeetConstraint;
	}

	private Collection<? extends Node> expand(final Node head, final Set<Element> explored) {
		final Set<Node> successors = new HashSet<>();
		for (final Element element : graph.getNodes().get(head.state)) {
			if (!explored.contains(element)) {
				final Node initNode = new Node(element, format("%s%s", head.action, element), head.depth + 1,
						head.cost + element.getCost(), head);
				successors.add(initNode);
			}
		}
		return successors;
	}

	protected abstract Node removeFront(Queue<Node> frontier);

	protected abstract Queue<Node> createFrontierCollection();

	private Queue<Node> makeNode(final Element startState) {
		final Queue<Node> queue = createFrontierCollection();
		final Node node = new Node(startState, format("%s", startState), 0, 0, null);
		queue.offer(node);
		return queue;
	}

}
