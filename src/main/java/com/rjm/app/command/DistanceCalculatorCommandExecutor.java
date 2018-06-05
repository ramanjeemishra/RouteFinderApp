package com.rjm.app.command;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.model.Element;
import com.rjm.search.SimpleGraphTraversal;
import com.rjm.store.GraphADT;

public class DistanceCalculatorCommandExecutor implements CommandExecutor {

	public String execute(RouteFinderCommand command, GraphADT<Element> graph)
			throws DestinationNotProvidedException, GraphNotInitializedException {
		return new SimpleGraphTraversal(graph).sumDistancesBetweenVertices(command.getStops());
	}

}
