package com.rjm.exception;

@SuppressWarnings("serial")
public class GraphNotInitializedException extends Exception {

	public GraphNotInitializedException() {
		super("Graph not initialized.");
	}

	public GraphNotInitializedException(final String exceptionMessage) {
		super(exceptionMessage);
	}

}
