package com.rjm.app;

import static com.rjm.store.GraphBuilder.createGraph;
import static java.nio.file.Files.lines;
import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.rjm.model.Element;
import com.rjm.store.GraphADT;
import com.rjm.store.GraphBuilder;

public class RouteExtractor {
	private GraphBuilder<Element> builder = null;

	public RouteExtractor() {
		builder = createGraph();
	}

	public GraphADT<Element> prepareRouteFromFile(Path path) throws IOException {
		try (Stream<String> stream = lines(path)) {
			stream.flatMap(route -> of(route.split(","))).map(s -> s.trim()).map(InputParameterAdapter::toRoute)
					.peek(System.out::println)
					.map(edge -> asList(new Element(edge.getFrom(), 0), new Element(edge.getTo(), edge.getCost())))// FIXME
					.forEach(builder::addRoute); // FIXME - NOT PARALLEL FRIENDLY
		}
		return builder.builtGraph();
	}

}
