package com.rjm.test;

import static com.rjm.store.GraphBuilder.createGraph;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.rjm.model.Element;
import com.rjm.store.GraphBuilder;

public class AbstractGraphTest {
	protected GraphBuilder<Element> builder = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		builder = createGraph();
	}
}
