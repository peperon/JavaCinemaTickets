package controllers;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.User;

import utils.WebAttributes;
import utils.WebPages;

@WebFilter("/*")
public class ApplicationListener implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String path = httpRequest.getServletPath();
		if (!path.equals("/login") && !path.equals("/login/") && 
				!path.equals("/register") && !path.equals("/register/")) {
			HttpSession session = httpRequest.getSession();
			User user = (User) session.getAttribute(WebAttributes.USER);
			if (user == null) {
				httpRequest.getRequestDispatcher(WebPages.LOGIN).forward(httpRequest, response);
				return;
			}
		}
		chain.doFilter(httpRequest, response);	
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
