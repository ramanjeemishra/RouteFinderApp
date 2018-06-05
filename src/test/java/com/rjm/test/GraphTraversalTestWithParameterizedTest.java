package com.rjm.test;

import static java.util.Arrays.asList;
import static java.util.stream.Stream.of;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.rjm.app.InputParameterAdapter;
import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.PathNotFoundException;
import com.rjm.model.Element;
import com.rjm.search.DepthFirstPathSerach;
import com.rjm.search.GraphSearch;
import com.rjm.search.GraphTraversal;
import com.rjm.search.SimpleGraphTraversal;
import com.rjm.search.UniformCostPathSerach;

class GraphTraversalTestWithParameterizedTest extends AbstractGraphTest {
	private static final String GRAPH_TEST_DATA_CSV = "/graphTestData.csv";

	@DisplayName("Verify distance between vertices. - #1, #2, #3, #4, #5, #6")
	@ParameterizedTest
	@CsvFileSource(resources = GRAPH_TEST_DATA_CSV)
	void calculateDistanceBetweenVertices(@AggregateWith(RouteAggregator.class) final String[] edges)
			throws DestinationNotProvidedException, PathNotFoundException {
		buildGraphFromInputSource(edges);
		assertNotNull(builder.builtGraph());
		final GraphTraversal traversal = new SimpleGraphTraversal(builder.builtGraph());
		traversal.printGraphRoute();
		// @formatter:off
		assertAll(
				() -> assertThrows(DestinationNotProvidedException.class,
						() -> traversal.sumDistancesBetweenVertices("A")),
				() -> assertEquals("9", traversal.sumDistancesBetweenVertices("A", "B", "C")),
				() -> assertEquals("5", traversal.sumDistancesBetweenVertices("A", "D")),
				() -> assertEquals("13", traversal.sumDistancesBetweenVertices("A", "D", "C")),
				() -> assertEquals("22", traversal.sumDistancesBetweenVertices("A", "E", "B", "C", "D")),
				() -> assertEquals("NO SUCH ROUTE", traversal.sumDistancesBetweenVertices("A", "E", "D")));
		// @formatter:on
	}
	
	@DisplayName("Verify time taken to travel between vertices. - #1, #2, #3, #4, #5, #6")
	@ParameterizedTest
	@CsvFileSource(resources = GRAPH_TEST_DATA_CSV)
	void calculateDistanceAndStopsBetweenVertices(@AggregateWith(RouteAggregator.class) final String[] edges)
			throws DestinationNotProvidedException, PathNotFoundException {
		buildGraphFromInputSource(edges);
		assertNotNull(builder.builtGraph());
		final GraphTraversal traversal = new SimpleGraphTraversal(builder.builtGraph());
		traversal.printGraphRoute();
		// @formatter:off
		assertAll(
				() -> assertThrows(DestinationNotProvidedException.class,
						() -> traversal.claculateTimeOfTravel("A")),
				() -> assertEquals("11", traversal.claculateTimeOfTravel("A", "B", "C")),
				() -> assertEquals("5", traversal.claculateTimeOfTravel("A", "D")),
				() -> assertEquals("15", traversal.claculateTimeOfTravel("A", "D", "C")),
				() -> assertEquals("28", traversal.claculateTimeOfTravel("A", "E", "B", "C", "D")),
				() -> assertEquals("NO SUCH ROUTE", traversal.claculateTimeOfTravel("A", "E", "D")));
		// @formatter:on
	}
	
	

	@DisplayName("Verify Trip counts between Source and Destination. - #6, #7 & #10")
	@ParameterizedTest
	@CsvFileSource(resources = GRAPH_TEST_DATA_CSV)
	void countTripBetweenSourceAndDesitnation(@AggregateWith(RouteAggregator.class) final String[] edges)
			throws DestinationNotProvidedException, PathNotFoundException {
		buildGraphFromInputSource(edges);
		assertNotNull(builder.builtGraph());
		final GraphSearch search = new DepthFirstPathSerach(builder.builtGraph());

		// @formatter:off
		assertAll(
				() -> assertEquals(2,
						search.countTrips("C", "C", (stops, cost) -> stops <= 3, (stops, cost) -> stops > 3)),
				() -> assertEquals(3,
						search.countTrips("A", "C", (stops, cost) -> stops == 4, (stops, cost) -> stops > 4)),
				() -> assertEquals(7,
						search.countTrips("C", "C", (stops, cost) -> cost < 30, (stops, cost) -> cost > 30)));
		// @formatter:on

	}

	@DisplayName("Length of the shortest route - #8 & #9")
	@ParameterizedTest
	@CsvFileSource(resources = GRAPH_TEST_DATA_CSV)
	void lentghOfshortestPathBetweenSourceDesitnation(@AggregateWith(RouteAggregator.class) final String[] edges)
			throws DestinationNotProvidedException, PathNotFoundException {
		buildGraphFromInputSource(edges);
		assertNotNull(builder.builtGraph());
		final GraphSearch search = new UniformCostPathSerach(builder.builtGraph());

		assertAll(() -> assertEquals(9, search.lengthOfShortestPath("A", "C")),
				() -> assertEquals(9, search.lengthOfShortestPath("B", "B")));
	}
	
	//FIXME - DRY
	private void buildGraphFromInputSource(final String[] edges) {
		debugInput(edges);
		of(edges).map(InputParameterAdapter::toRoute)
				.map(edge -> asList(new Element(edge.getFrom(), 0), new Element(edge.getTo(), edge.getCost())))
				.forEach(builder::addRoute);
	}

	private void debugInput(final String[] nodes) {
		of(nodes).forEach(System.out::println);
	}
	
}
