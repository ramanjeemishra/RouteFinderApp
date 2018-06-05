package com.rjm.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class VertexNotFoundException extends Exception {
	public VertexNotFoundException(final Object v) {
		super(format("No vertex [%s].", v));
	}
}
