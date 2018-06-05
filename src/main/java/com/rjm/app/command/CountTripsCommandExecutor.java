package com.rjm.app.command;

import static com.rjm.app.command.RouteFinderCommand.Constraints.*;
import static java.lang.String.*;

import java.util.function.BiPredicate;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.exception.UnexpectedConstraintException;
import com.rjm.model.Element;
import com.rjm.search.DepthFirstPathSerach;
import com.rjm.store.GraphADT;

public class CountTripsCommandExecutor implements CommandExecutor {

	public String execute(RouteFinderCommand command, GraphADT<Element> graph) throws DestinationNotProvidedException,
			GraphNotInitializedException, PathNotFoundException, UnexpectedConstraintException {
		return valueOf(new DepthFirstPathSerach(graph).countTrips(command.getStops()[0], command.getStops()[1],
				extractResultAcceptableConditionFromCommand(command), extractStopConditionFromCommand(command)));
	}

	private BiPredicate<Integer, Integer> extractResultAcceptableConditionFromCommand(RouteFinderCommand command)
			throws UnexpectedConstraintException {
		
		if (MAX_STOPS == command.getConstraintType()) {
			return (stops, cost) -> stops <= command.getConstraintParameter();
		}

		if (STOPS == command.getConstraintType()) {
			return (stops, cost) -> stops == command.getConstraintParameter();
		}

		if (MAX_DISTANCE == command.getConstraintType()) {
			return (stops, cost) -> cost < command.getConstraintParameter();
		}

		throw new UnexpectedConstraintException(format("%s command type not expected.", command.getConstraintType()));

	}

	private BiPredicate<Integer, Integer> extractStopConditionFromCommand(RouteFinderCommand command)
			throws UnexpectedConstraintException {

		if (MAX_STOPS == command.getConstraintType()) {
			return (stops, cost) -> stops > command.getConstraintParameter();
		}

		if (STOPS == command.getConstraintType()) {
			return (stops, cost) -> stops > command.getConstraintParameter();
		}

		if (MAX_DISTANCE == command.getConstraintType()) {
			return (stops, cost) -> cost > command.getConstraintParameter();
		}

		throw new UnexpectedConstraintException(format("%s command type not expected.", command.getConstraintType()));

	}

}
