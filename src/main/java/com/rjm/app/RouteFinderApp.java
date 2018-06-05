package com.rjm.app;

import static com.rjm.app.command.CommandExecutorFactory.getExecutor;
import static java.nio.file.Files.write;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.rjm.app.command.CommandsExtractor;
import com.rjm.app.command.RouteFinderCommand;
import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.exception.UnexpectedConstraintException;
import com.rjm.model.Element;
import com.rjm.store.GraphADT;

public class RouteFinderApp {

	private RouteFinderApp() {
	}

	public static void main(final String... args)
			throws IOException, DestinationNotProvidedException, GraphNotInitializedException {

		if (!checkArguments(args)) return;
		
		final List<Path> files = extractPathsOfInputAndOutput(args);
		write(files.get(2), 
				executeCommand(
						new CommandsExtractor().extractCommandFromFile(files.get(1)),
						new RouteExtractor().prepareRouteFromFile(files.get(0))
						)
			);

	}

	private static List<Path> extractPathsOfInputAndOutput(final String... args) {
		System.out.println("Runing route finder with arguments : ");
		return of(args).peek(System.out::println).map(Paths::get).map(Path::toAbsolutePath).peek(System.out::println)
				.collect(toList());
	}

	private static boolean checkArguments(final String... args) {
		if (args.length != 3) {
			System.out.println("Insufficinet arguments :RouteFinderApp "
					+ "<<Route_Input_file>> " 
					+ "<<Command_file>> "
					+ "<<output_file>>");
			return false;
		}
		return true;
	}

	private static List<String> executeCommand(final List<RouteFinderCommand> commands, 
				final GraphADT<Element> graph) throws RuntimeException {
		return commands.stream().map((command) -> {
			try {
				return getExecutor(command).execute(command, graph);
			} catch (DestinationNotProvidedException | GraphNotInitializedException | PathNotFoundException
					| UnexpectedConstraintException e) {
				e.printStackTrace();//TODO Logger
			}
			return null;
		}).peek(System.out::println).collect(toList());
	}

}
