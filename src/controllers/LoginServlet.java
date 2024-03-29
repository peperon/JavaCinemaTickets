package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import model.User;

import utils.UserUtils;
import utils.WebAttributes;
import utils.WebPages;

import db.UsersDataProvider;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private UsersDataProvider dataProvider;
	
	@Override
	public void init() throws ServletException {
		dataProvider = new UsersDataProvider();
		super.init();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UserUtils.isUserLoggedIn(request)) { // check if user is already logged in
			request.getRequestDispatcher(WebPages.HOME).forward(request, response);
			return;
		}
		RequestDispatcher view = request.getRequestDispatcher(WebPages.LOGIN);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UserUtils.isUserLoggedIn(request)) { // check if user is already logged in
			request.getRequestDispatcher(WebPages.HOME).forward(request, response);
			return;
		}
		String userName = request.getParameter(WebAttributes.USER_NAME);
		String password = request.getParameter(WebAttributes.PASSWORD);
		boolean error = false;
		if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
			error = true;
		} else {
			User user = dataProvider.getUserByName(userName);
			if (user == null || !UserUtils.getSHA256HashString(password).equals(user.getPassword())) {
				error = true;
			} else {
				request.getSession().setAttribute(WebAttributes.USER, user);
			}
		}
		
		RequestDispatcher view = null;
		if (error) {
			request.setAttribute(WebAttributes.ERROR_MESSAGE, 
					"The username or password you entered is incorrect.");
			view = request.getRequestDispatcher(WebPages.LOGIN);
			
		} else {
			request.setAttribute(WebAttributes.SYSTEM_MESSAGE, 
					"You've successfully logged on.");
			view = request.getRequestDispatcher(WebPages.HOME);
		}
		view.forward(request, response);
	}

}
