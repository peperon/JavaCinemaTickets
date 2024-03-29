package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

public class UserUtils {
	public static final int MIN_PASSWORD_LENGTH = 6;
	public static final int MAX_PASSWORD_LENGTH = 30;
	
	public static String getSHA256HashString(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
        return sb.toString();
	}
	
	public static boolean isUserLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(WebAttributes.USER);
		return user != null;
	}
	
	public static boolean isUserInRole(HttpServletRequest request, int requiredAccessLevel) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(WebAttributes.USER);
		if (user == null || user.getUserTypeId() < requiredAccessLevel) {
			return false;
		} else {
			return true;
		}
	}

}
