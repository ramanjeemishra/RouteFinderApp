package com.rjm.search;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;

public interface GraphTraversal {

	// FIXME Throw exception for large graphs - using stream???
	String graphRouteAsSimpleString();

	String sumDistancesBetweenVertices(String from, String... viaTo)
			throws DestinationNotProvidedException, GraphNotInitializedException;

	String sumDistancesBetweenVertices(String[] split)
			throws DestinationNotProvidedException, GraphNotInitializedException;

	// FIXME - use logger
	void printGraphRoute();

	String claculateTimeOfTravel(String from, String ...viaTo) throws DestinationNotProvidedException, GraphNotInitializedException;

}
