package com.rjm.app.command;

import static java.lang.Integer.valueOf;

public class RouteFinderCommand {
	String COMMAND_FORMAT = "COMMAND ROUTE CONSTRAINTS CONSTRAINT_TYPE VALUE";// FIXME IMPROVE ROBUSTNESS

	enum Commands {
		CALCULATE_DISTANCE, COUNT_TRIPS, SHORTEST_PATH
	};

	enum Constraints {
		DISTANCE, MAX_STOPS, STOPS, MAX_DISTANCE
	};

	private Commands command;
	private String route;
	private Constraints constraintType;
	private int constraintParameter;

	RouteFinderCommand(String command, String route, String constraintType, String constraintParameter) {
		this.command = Commands.valueOf(command);
		this.route = route;
		this.constraintType = "" != constraintType ? Constraints.valueOf(constraintType) : null;
		this.constraintParameter = "" == constraintParameter ? 0 : valueOf(constraintParameter);
	}

	public RouteFinderCommand(String[] commandAndParameters) {
		this(commandAndParameters[0], commandAndParameters[1],
				commandAndParameters.length > 2 ? commandAndParameters[2] : "",
				commandAndParameters.length > 3 ? commandAndParameters[3] : "");
	}

	@Override
	public String toString() {
		return "Command [" + command + ",  " + route + ",  " + constraintType + ",  " + constraintParameter + "]";
	}

	public Commands getCommand() {
		return command;
	}

	public String getRoute() {
		return route;
	}

	public Constraints getConstraintType() {
		return constraintType;
	}

	public int getConstraintParameter() {
		return constraintParameter;
	}

	public String[] getStops() {
		return getRoute().split("-");
	}
}
