package com.rjm.test;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class RouteAggregator implements ArgumentsAggregator {
	@Override
	public String[] aggregateArguments(final ArgumentsAccessor arguments, final ParameterContext context) {
		return (String[]) arguments.toArray();
	}
}
