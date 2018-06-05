package com.rjm.app.command;

import static com.rjm.app.command.RouteFinderCommand.Commands.*;

import java.util.HashMap;
import java.util.Map;

import com.rjm.app.command.RouteFinderCommand.Commands;

public class CommandExecutorFactory {

	Map<Commands, CommandExecutor> executorMap = new HashMap<>();
	private static CommandExecutorFactory factory = new CommandExecutorFactory();

	private CommandExecutorFactory() {
		executorMap.put(CALCULATE_DISTANCE, new DistanceCalculatorCommandExecutor());
		executorMap.put(COUNT_TRIPS, new CountTripsCommandExecutor());
		executorMap.put(SHORTEST_PATH, new ShortestPathCommandExecutor());
	}

	public static CommandExecutor getExecutor(RouteFinderCommand command) {
		return factory.executorMap.get(command.getCommand());
	}
}
