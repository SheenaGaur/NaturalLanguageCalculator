package com.app.nlc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import com.app.nlc.exception.CalculatorInputFormatException;
import com.app.nlc.exception.DivisionByZeroException;
import com.app.nlc.utility.NaturalLangCalculator;

/**
 * Main class from where the program execution begins. This program performs
 * natural language text calculations. For example- Input: two plus three
 * Result: 4.0
 *
 * @author Sheena Gaur
 */
public class App {
	public static void main(String[] args) {
		// Input statements
		System.out.println("Please enter the natural language statement:");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine().trim().toLowerCase();
		try {
			NaturalLangCalculator naturalLangCalculator = new NaturalLangCalculator(input);
			// To check if the input is in the required format.
			naturalLangCalculator.checkInputFormat();
			// Perform Calculation and get result.
			BigDecimal result = new BigDecimal(naturalLangCalculator.performCalculation());
			// Round off the result to two decimal places
			double finalRes = result.setScale(2, RoundingMode.HALF_UP).doubleValue();
			System.out.println("Result:" + finalRes);

		} catch (CalculatorInputFormatException e) {
			System.err.println(e.getMessage());
		} catch (DivisionByZeroException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
