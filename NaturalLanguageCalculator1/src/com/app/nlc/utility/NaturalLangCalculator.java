package com.app.nlc.utility;

import com.app.nlc.exception.CalculatorInputFormatException;
import com.app.nlc.exception.DivisionByZeroException;

/**
 * This class performs the calculations based on the input text and also checks
 * format of the input text, throwing the required exception where needed.
 * 
 * @author Sheena Gaur
 *
 */
public class NaturalLangCalculator {

	/**
	 * Input string
	 */
	private String input;

	/**
	 * Language constants object
	 */
	NaturalLangConstants langConstants;

	/**
	 * Parameterized constructor of the class that takes the input string as an
	 * argument.
	 * 
	 * @param input
	 */
	public NaturalLangCalculator(String input) {
		this.input = input;
		langConstants = new NaturalLangConstants();
	}

	/**
	 * Method checks the formatting of the words of the input.
	 * 
	 * @throws CalculatorInputFormatException
	 * @throws DivisionByZeroException 
	 */
	public void checkInputFormat() throws CalculatorInputFormatException, DivisionByZeroException {
		// Check if input String is in the correct format and throw an exception
		// if required.
		String incorrectString = langConstants.checkInputString(input);
		if (!incorrectString.isEmpty()) {
			throw new CalculatorInputFormatException(incorrectString);
		}
		if (input.contains("over zero") || input.contains("divided-by zero")) {
			throw new DivisionByZeroException();
		}
	}

	/**
	 * Method performs mathematical calculation on the input natural language
	 * text
	 * 
	 * @return result
	 */
	public String performCalculation() throws Exception {
		// Check for presence of a higher order operator
		boolean containsPrecedence = checkPrecedence(input);
		String result = "";
		//
		String[] inputArr = input.split("\\s+");

		int arrLen = inputArr.length;

		// The next part is only performed if a higher order operator is
		// present.
		if (containsPrecedence) {
			// Create a boolean array to mark the places where the higher order
			// operator is present.
			boolean[] op = new boolean[arrLen];
			for (int i = 0; i < arrLen; i++) {
				if (langConstants.getAcceptableHigherOperatorMap().containsKey(inputArr[i])) {
					op[i] = true;
				}
			}
			// While the calculations for higher order operators is still
			// pending
			while (containsPrecedence) {
				// Get the location of the first higher order operator. Checking
				// from left to right
				int i = getLocOfHigerPrecOp(op, arrLen);
				// If there exits higher order operators only then perform the
				// calculation.
				if (i != -1) {
					// Apply the operation mentioned in the text.
					String res = applyOperation(inputArr[i], inputArr[i - 1], inputArr[i + 1]);
					// Set the result in place of the operation.
					inputArr[i - 1] = res;
					// Get starting point for next operator.
					int nextNumberLoc = (arrLen < (i + 2)) ? arrLen : (i + 2);
					for (int j = nextNumberLoc; j < arrLen; j++) {
						inputArr[j - 2] = inputArr[j];
						op[j - 2] = op[j];
					}
					// Reduce the size of the array.
					arrLen -= 2;
				} else {
					// When no more higher order operators present set boolean
					// to false.
					containsPrecedence = false;
				}
			}
		}

		// Perform general calculations for the numbers left in the input text
		// from left to right.
		for (int i = 1; i < arrLen;) {
			String res = applyOperation(inputArr[i], inputArr[i - 1], inputArr[i + 1]);
			inputArr[i - 1] = res;
			int nextNumberLoc = (arrLen < (i + 2)) ? arrLen : (i + 2);
			for (int j = nextNumberLoc; j < arrLen; j++) {
				inputArr[j - 2] = inputArr[j];
			}
			arrLen -= 2;
			i = 1;
		}
		// As the final result is left on the 0th index that is taken as the
		// final result.
		result = inputArr[0];

		return result;
	}

	/**
	 * Checks if higher precedence operators are present like * and /.
	 * 
	 * @param input
	 * @return boolean
	 */
	private boolean checkPrecedence(String input) {
		boolean containsPrecedence = false;
		if (input.contains("times") || input.contains("multiplied-by") || input.contains("divided-by")
				|| input.contains("over")) {
			containsPrecedence = true;
		}

		return containsPrecedence;
	}

	/**
	 * Method performs the mathematical calculation on the basis of operator
	 * provided. The result is a String so that it can be appended back into the
	 * input array.
	 * 
	 * @param op
	 * @param text1
	 * @param text2
	 * @return Result as String
	 */
	private String applyOperation(String op, String text1, String text2) {
		// If the number is from the initial input convert it to its value
		// otherwise convert to Double.
		if (langConstants.getAcceptableTextMap().containsKey(text1)) {
			text1 = langConstants.getAcceptableTextMap().get(text1);
		}
		if (langConstants.getAcceptableTextMap().containsKey(text2)) {
			text2 = langConstants.getAcceptableTextMap().get(text2);

		}
		// Convert input to double
		double operand1 = Double.valueOf(text1);
		double operand2 = Double.valueOf(text2);
		double res = 0;
		// Apply the required operation.
		switch (op) {
		case "times":
		case "multiplied-by":
			res = operand1 * operand2;
			break;
		case "divided-by":
		case "over":
			res = operand1 / operand2;
			break;
		case "add":
		case "plus":
			res = operand1 + operand2;
			break;
		case "less":
		case "subtract":
		case "minus":
			res = operand1 - operand2;
			break;
		}

		return String.valueOf(res);
	}

	/**
	 * Returns the first location of the higher order operator using the boolean
	 * array. Returns -1 if no such operator is present.
	 * 
	 * @param op
	 * @param len
	 * @return location
	 */
	private int getLocOfHigerPrecOp(boolean[] op, int len) {
		int loc = -1;
		for (int i = 0; i < len; i++) {
			if (op[i] == true) {
				loc = i;
				// Once operator found set its value to false
				op[i] = false;
				break;
			}
		}
		return loc;
	}

}
