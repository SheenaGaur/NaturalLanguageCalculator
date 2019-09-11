package com.app.nlc.exception;

/**
 * Exception class that checks the formatting to ensure that the no division by zero occurs.
 * 
 * @author Sheena Gaur
 *
 */
public class DivisionByZeroException extends Exception {

	/**
	 * Default serial version 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Default error message string.
	 */
	private String errorMessage = "Equation contains division by zero. Please enter a new input.";
	
	/**
	 * Default Constructor
	 */
	public DivisionByZeroException() {
		super();
	}
	

	/**
	 * Overriden getMessage method
	 */
	@Override
	public String getMessage(){
		return errorMessage;
	}

}
