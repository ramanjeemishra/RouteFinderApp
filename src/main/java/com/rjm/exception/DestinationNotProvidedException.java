package com.rjm.exception;

/**
 * When calling the search or route finding method without providing
 * destination, then {@DestinationNotProvidedException} exception will be
 * thrown.
 */
@SuppressWarnings("serial")
public final class DestinationNotProvidedException extends Exception {

	public DestinationNotProvidedException() {
		super("There is no desitination provided...");
	}
}
