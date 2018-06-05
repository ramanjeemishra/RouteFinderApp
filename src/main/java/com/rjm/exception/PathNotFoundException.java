package com.rjm.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class PathNotFoundException extends Exception {
	public PathNotFoundException(final String from, final String to) {
		super(format("No Path from %s to %s.", from, to));
	}

}
