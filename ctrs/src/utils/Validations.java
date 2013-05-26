package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Validations {
	
	public static String validateText(String attributeName, String text, 
			int minLength, int maxLength, boolean isRequired) {
		if (!isRequired && (text == null || text.isEmpty())) {
			return "";
		} else if (isRequired && (text == null || text.isEmpty())) {
			return attributeName + " is required! ";
		}
		String errorMessage = "";
		if (text.length() < minLength) {
			errorMessage += attributeName + " should be at least " + minLength + " characters long! ";
		} else if (text.length() > maxLength) {
			errorMessage += attributeName + " should be at least " + maxLength + " characters long! ";
		}
		return errorMessage;
	}
	
	
	public static void main(String parameters[]) throws ParseException {
		
	}
}
