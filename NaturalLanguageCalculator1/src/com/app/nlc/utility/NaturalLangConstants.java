package com.app.nlc.utility;

import java.util.HashMap;

/**
 * Class containing the list of constants acceptable by the program.
 * 
 * @author Sheena Gaur
 *
 */
public class NaturalLangConstants {
	/**
	 * HashMaps that stores the acceptable text input.
	 */
	private HashMap<String, String> acceptableTextMap = new HashMap<String, String>();
	private HashMap<String, String> acceptableHigherOperatorMap = new HashMap<String, String>();
	private HashMap<String, String> acceptableLowerOperatorMap = new HashMap<String, String>();

	/**
	 * Default Constructor for the class. It also initializes the acceptable
	 * strings hashmaps
	 */
	public NaturalLangConstants() {
		initializeAcceptableTextMap();
	}

	/**
	 * Getter method for getAcceptableNumberMap
	 * 
	 * @return acceptableTextMap
	 */
	public HashMap<String, String> getAcceptableTextMap() {
		return acceptableTextMap;
	}

	/**
	 * Getter method for acceptableOperatorMap.
	 * 
	 * @return acceptableHigherOperatorMap
	 */
	public HashMap<String, String> getAcceptableHigherOperatorMap() {
		return acceptableHigherOperatorMap;
	}

	/**
	 * Getter method for acceptableLowerOperatorMap.
	 * @return acceptableLowerOperatorMap
	 */
	public HashMap<String, String> getAcceptableLowerOperatorMap() {
		return acceptableLowerOperatorMap;
	}

	/**
	 * Initialize the hashmaps with the strings
	 */
	private void initializeAcceptableTextMap() {
		acceptableTextMap.put("zero", "0");
		acceptableTextMap.put("one", "1");
		acceptableTextMap.put("two", "2");
		acceptableTextMap.put("three", "3");
		acceptableTextMap.put("four", "4");
		acceptableTextMap.put("five", "5");
		acceptableTextMap.put("six", "6");
		acceptableTextMap.put("seven", "7");
		acceptableTextMap.put("eight", "8");
		acceptableTextMap.put("nine", "9");
		acceptableTextMap.put("ten", "10");
		acceptableLowerOperatorMap.put("add", "symbol");
		acceptableLowerOperatorMap.put("plus", "symbol");
		acceptableLowerOperatorMap.put("subtract", "symbol");
		acceptableLowerOperatorMap.put("minus", "symbol");
		acceptableLowerOperatorMap.put("less", "symbol");
		acceptableHigherOperatorMap.put("times", "symbol");
		acceptableHigherOperatorMap.put("multiplied-by", "symbol");
		acceptableHigherOperatorMap.put("over", "symbol");
		acceptableHigherOperatorMap.put("divided-by", "symbol");
	}

	/**
	 * This method checks if the input string has the right format or not. It
	 * also returns the incorrect string if found.
	 * 
	 * @param input
	 * @return Incorrect String
	 */
	public String checkInputString(String input) {
		String[] inputArr = input.split("\\s+");
		String incorrectString = new String();
		if (inputArr.length < 3) {
			incorrectString = "Missing data in input";
			return incorrectString;
		}
		for (int i = 0; i < inputArr.length; i++) {
			if (!(acceptableTextMap.containsKey(inputArr[i]) || acceptableLowerOperatorMap.containsKey(inputArr[i])
					|| acceptableHigherOperatorMap.containsKey(inputArr[i]))) {
				incorrectString = inputArr[i];
				break;
			}
			if (i % 2 != 0 && !(acceptableLowerOperatorMap.containsKey(inputArr[i])
					|| acceptableHigherOperatorMap.containsKey(inputArr[i]))) {
				incorrectString = "missing operator";
				break;
			}
			if (i % 2 == 0 && !acceptableTextMap.containsKey(inputArr[i])) {
				incorrectString = "missing number for operator";
				break;
			}
			if (i == inputArr.length - 1 && (acceptableLowerOperatorMap.containsKey(inputArr[i])
					|| acceptableHigherOperatorMap.containsKey(inputArr[i]))) {
				incorrectString = "missing number for operator";
			}
		}
		return incorrectString;
	}

}
