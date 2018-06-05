package com.rjm.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class EdgeNotFoundException extends Exception {

	public EdgeNotFoundException(final Object v1, final Object v2) {
		super(format("[%s] -> [%s]", v1, v2));
	}

}
