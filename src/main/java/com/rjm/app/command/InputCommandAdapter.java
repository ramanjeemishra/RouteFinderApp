package com.rjm.app.command;

public class InputCommandAdapter {
	private InputCommandAdapter() {
	}

	public static RouteFinderCommand command(String... commandAndParameters) {

		return new RouteFinderCommand(commandAndParameters);

	}
}
