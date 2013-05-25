package utils;

import javax.servlet.http.HttpServletRequest;

public class LogUtils {
	
	public static void logPath(HttpServletRequest request) {
		System.out.println(request.getPathInfo());
	}
}
