package utils;

import java.text.ParseException;

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
	
	public static String escapeHtml(String html) {
		  if (html == null) {
			  return html;
		  }
		  html = html.replaceAll("&", "&amp;");
		  html = html.replaceAll("\"", "&quot;");
		  html = html.replaceAll("'", "&#039;");
		  html = html.replaceAll("<", "&lt;");
		  html = html.replaceAll(">", "&gt;");
		  return html;
	}
	
	
	public static void main(String parameters[]) throws ParseException {
		
	}
}
