package com.rjm.app;

import static java.lang.Integer.valueOf;
import static java.lang.String.valueOf;

import com.rjm.model.Route;

public class InputParameterAdapter {
	private InputParameterAdapter() {
	}

	public static Route toRoute(final String s) {
		return new Route(valueOf(s.charAt(0)), valueOf(s.charAt(1)), valueOf(valueOf(s.charAt(2))));
	}
}
