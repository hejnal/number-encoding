package com.wojtekhejna.exceptions;

public class InvalidDigitException extends Exception {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 132223L;

	public InvalidDigitException(String message) {
		super(message);
	}

	public InvalidDigitException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
