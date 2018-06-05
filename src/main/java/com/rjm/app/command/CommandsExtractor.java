package com.rjm.app.command;

import static java.nio.file.Files.lines;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class CommandsExtractor {

	public List<RouteFinderCommand> extractCommandFromFile(Path path) throws IOException {
		// @formatter:off
		try (Stream<String> stream = lines(path)) {
			return stream
					.map(command -> command.split(" "))
					.map(RouteFinderCommand::new)
					.peek(System.out::println)
					.collect(toList());
		}
		// @formatter:on

	}

}
