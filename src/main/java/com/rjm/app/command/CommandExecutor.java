package com.rjm.app.command;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.exception.UnexpectedConstraintException;
import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public interface CommandExecutor {
	public String execute(RouteFinderCommand command, GraphADT<Element> graph)
			throws DestinationNotProvidedException, GraphNotInitializedException, PathNotFoundException, UnexpectedConstraintException;
}
