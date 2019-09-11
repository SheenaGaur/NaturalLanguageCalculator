package com.app.nlc.exception;

/**
 * Exception class that checks the formatting for the input string.
 * 
 * @author Sheena Gaur
 *
 */
public class CalculatorInputFormatException extends Exception {
	/**
	 * Default serial version.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Default error message string.
	 */
	private String errorMessage = "There is a parsing error with the input String. The incorrect area is-";

	/**
	 * Parameterized constructor of the class that takes the appropriate error
	 * message as an argument.
	 * 
	 * @param errorString
	 */
	public CalculatorInputFormatException(String errorString) {
		this.errorMessage = this.errorMessage + errorString + ". \nPlease enter the input again.";
	}

	/**
	 * Overriden getMessage method
	 */
	@Override
	public String getMessage() {
		return errorMessage;
	}

}
