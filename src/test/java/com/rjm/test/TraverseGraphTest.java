package com.rjm.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rjm.exception.DestinationNotProvidedException;
import com.rjm.exception.EdgeNotFoundException;
import com.rjm.exception.GraphNotInitializedException;
import com.rjm.exception.VertexNotFoundException;
import com.rjm.model.Element;
import com.rjm.search.SimpleGraphTraversal;
import com.rjm.store.GraphADT;

class TraverseGraphTest extends AbstractGraphTest {

	private GraphADT<Element> buildSimpleGraph() {
		return builder.addRoute(new Element("S", 0), new Element("D", 5))
				.addRoute(new Element("S", 0), new Element("T", 2)).addRoute(new Element("T", 0), new Element("E", 2))
				.addRoute(new Element("E", 0), new Element("D", 3)).builtGraph();
	}

	@DisplayName("Verify Exception - When Graph Is Not Initialized")
	@Test
	void initializationException() {
		assertThrows(GraphNotInitializedException.class, () -> {
			builder.addElement(null, null);
		});
	}

	@DisplayName("Verify Graph Initialization")
	@Test
	void initializeGraph() {
		assertNotNull(buildSimpleGraph());
	}

	@DisplayName("Verify Graph is empty")
	@Test
	void graphIsEmpty() {
		assertTrue(builder.builtGraph().isEmpty());
	}

	@DisplayName("Verify Adjacency list for Empty Graph")
	@Test
	void adjacenyListForEmptyGraph() {
		assertThrows(GraphNotInitializedException.class,
				() -> builder.builtGraph().getAdjacenyList(new Element("S", 0)));
	}

	@DisplayName("Verify Graph is Not empty")
	@Test
	void graphIsNotEmpty() {
		assertFalse(buildSimpleGraph().isEmpty());
	}

	@DisplayName("Verify Vertex Not Found Exception")
	@Test
	void vertexNotFoundException() {
		assertThrows(VertexNotFoundException.class, () -> buildSimpleGraph().getAdjacenyList(new Element("P")));
	}

	@DisplayName("Verify Edge Not Found Exception")
	@Test
	void edgeNotFoundException() {
		assertThrows(EdgeNotFoundException.class, () -> buildSimpleGraph().getEdge(new Element("S"), new Element("E")));
	}

	@DisplayName("Verify Adjacency list for Non-Empty Graph")
	@Test
	void adjacenyListForNonEmptyGraph() {
		assertDoesNotThrow(() -> buildSimpleGraph().getAdjacenyList(new Element("S", 0)));
	}

	@DisplayName("Verify Adjacency list size")
	@Test
	void adjacenyListSizeNonEmptyGraph() throws GraphNotInitializedException, VertexNotFoundException {
		assertEquals(2, buildSimpleGraph().getAdjacenyList(new Element("S", 0)).size());
	}

	@DisplayName("Verify Graph Route")
	@Test
	void traverseGraphListAsString() throws GraphNotInitializedException {
		assertEquals("\nS-->DT\nT-->E\nE-->D", new SimpleGraphTraversal(buildSimpleGraph()).graphRouteAsSimpleString());
	}

	@DisplayName("Verify Graph Edge - Distance")
	@Test
	void singleEdgeWeight() throws GraphNotInitializedException, DestinationNotProvidedException, EdgeNotFoundException,
			VertexNotFoundException {
		assertEquals("2", new SimpleGraphTraversal(buildSimpleGraph()).sumDistancesBetweenVertices("S", "T"));
	}

	@DisplayName("Verify Cost for multiple Connectted Graph Edges")
	@Test
	void multipleEdgesCostSum() throws GraphNotInitializedException, DestinationNotProvidedException,
			EdgeNotFoundException, VertexNotFoundException {
		assertEquals("7", new SimpleGraphTraversal(buildSimpleGraph()).sumDistancesBetweenVertices("S", "T", "E", "D"));
	}

}
