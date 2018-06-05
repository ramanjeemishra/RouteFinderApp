package com.rjm.app.command;

import static java.lang.String.valueOf;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.model.Element;
import com.rjm.search.UniformCostPathSerach;
import com.rjm.store.GraphADT;

public class ShortestPathCommandExecutor implements CommandExecutor {

	public String execute(RouteFinderCommand command, GraphADT<Element> graph)
			throws DestinationNotProvidedException, GraphNotInitializedException, PathNotFoundException {
		return valueOf(
				new UniformCostPathSerach(graph)
				.lengthOfShortestPath(
						command.getStops()[0], //Start
						command.getStops()[1] //Stop
				));
	}

}
