package com.rjm.search;

import java.util.function.BiPredicate;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.PathNotFoundException;

public interface GraphSearch {
	int findDistance(String from, String to, BiPredicate<Integer, Integer> resultAcceptableCondition,
			BiPredicate<Integer, Integer> stopCondition) throws DestinationNotProvidedException, PathNotFoundException;

	int countTrips(String from, String to, BiPredicate<Integer, Integer> resultAcceptableCondition,
			BiPredicate<Integer, Integer> stopCondition) throws DestinationNotProvidedException, PathNotFoundException;

	int lengthOfShortestPath(String from, String to) throws DestinationNotProvidedException, PathNotFoundException;
}
