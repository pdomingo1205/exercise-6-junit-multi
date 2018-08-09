package com.srv;

import java.util.InputMismatchException;
public class InputValidation{

	private static Integer min = 0;
	private static Integer max = 0;

	private InputValidation(){

	}

	public static class Inner{
		public static String mockStringInput(String text) {
			if (text.isEmpty()) {
				text = null;
			}
			return text;
    	}

		public static Boolean isInRange(Integer number){
			Boolean boolVal = true;
			if(number < 0 || number > 20){
				boolVal = false
			}
			return boolVal;
		}
	}

	public static void setRange(Integer newMin, Integer newMax){
		min = newMin;
		max = newMax;
	}

	public static String stringInput(String text){
		if (text.isEmpty()) {
			text = null;
		}
		return text;
	}

	public static Integer getBoundIntegerFromUser(IntegerAsker asker) {
	    Integer input = asker.ask(String.format("Input a number between %s - %s", String.valueOf(min), String.valueOf(max)));
		while (input < min || input > max){
			input = asker.ask("Wrong number, try again.");
		}
	    return input;
	}

}
