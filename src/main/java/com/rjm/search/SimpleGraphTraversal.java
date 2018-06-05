package com.rjm.search;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.Arrays.copyOfRange;

import java.util.stream.Stream;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.EdgeNotFoundException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.VertexNotFoundException;
import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public class SimpleGraphTraversal implements GraphTraversal {

	private final GraphADT<Element> graph;

	public SimpleGraphTraversal(final GraphADT<Element> graph) {
		this.graph = graph;
	}

	// TODO Throw exception for large graphs - using stream???
	@Override
	public String graphRouteAsSimpleString() {
		final StringBuilder sb = new StringBuilder();
		graph.getNodes().keySet().stream().peek(key -> {
			sb.append(format("\n%s-->", key));
		}).flatMap(key -> graph.getNodes().get(key).stream()).forEach(sb::append);
		return sb.toString();
	}

	@Override
	public String sumDistancesBetweenVertices(final String from, final String... viaTo)
			throws DestinationNotProvidedException, GraphNotInitializedException {

		validateAndLogArguments(from, viaTo);
		
		int edgeTotalCost = 0;
		
		Element startVertex = new Element(from);
		for (final String dest : viaTo) {
			final Element destVertex = new Element(dest);
			try {//TODO - Use stream aggregator
				edgeTotalCost += graph.getEdge(startVertex, destVertex).getCost();
			} catch (EdgeNotFoundException | VertexNotFoundException e) {
				return "NO SUCH ROUTE";
			}
			startVertex = destVertex;
		}
		return valueOf(edgeTotalCost);
	}

	private void validateAndLogArguments(final String from, final String... viaTo)
			throws DestinationNotProvidedException {
		if ((viaTo == null) | (viaTo.length == 0)) {
			throw new DestinationNotProvidedException();
		}
		
		logArguments(from, viaTo);
	}
	

	@Override
	public String claculateTimeOfTravel(String from, String... viaTo)
			throws DestinationNotProvidedException, GraphNotInitializedException {
		
		validateAndLogArguments(from, viaTo);

		int edgeTotalCost = 0;
		int stops =0;

		Element startVertex = new Element(from);
		for (final String dest : viaTo) {
			final Element destVertex = new Element(dest);
			try {// TODO - Use stream aggregator
				edgeTotalCost += graph.getEdge(startVertex, destVertex).getCost();
				stops++;
			} catch (EdgeNotFoundException | VertexNotFoundException e) {
				return "NO SUCH ROUTE";
			}
			startVertex = destVertex;
		}
		//FIXME - Optimize stops implementation
		return valueOf(edgeTotalCost + (stops-1)*2);
	}

	private void logArguments(final String from, final String... viaTo) {
		System.out.printf("\nFinding distance from %s", from  );
		Stream.of(viaTo).map(s -> format("->%s ", s)).forEach(System.out::print);
	}

	
	@Override
	public String sumDistancesBetweenVertices(final String[] stops)
			throws DestinationNotProvidedException, GraphNotInitializedException {
		return sumDistancesBetweenVertices(stops[0], copyOfRange(stops, 1, stops.length));
	}
	// TODO - use logger
	@Override
	public void printGraphRoute() {
		System.out.println(format("Routes - %s.", graphRouteAsSimpleString()));
	}

}
