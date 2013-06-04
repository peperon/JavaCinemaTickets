package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

import db.UsersDataProvider;

import utils.LogUtils;
import utils.UserTypes;
import utils.UserUtils;
import utils.Validations;
import utils.WebAttributes;
import utils.WebPages;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private UsersDataProvider usersDataProvider;
    
    @Override
    public void init() throws ServletException {
    	usersDataProvider = new UsersDataProvider();
    	super.init();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UserUtils.isUserLoggedIn(request)) { // check if user is already logged in
			request.getRequestDispatcher(WebPages.HOME).forward(request, response);
			return;
		}
		LogUtils.logPath(request);
		RequestDispatcher view = request.getRequestDispatcher(WebPages.REGISTER);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (UserUtils.isUserLoggedIn(request)) { // check if user is already logged in
			request.getRequestDispatcher(WebPages.HOME).forward(request, response);
			return;
		}
		LogUtils.logPath(request);
		String username = request.getParameter("user_name");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		
		boolean error = false;
		String errorMessage = "";
		
		if (username == null || username.trim().isEmpty()) {
			error = true;
			errorMessage += "Username is required! ";
		} else {
			username = username.trim();
			errorMessage += Validations.validateText("Username", username, 3, 50, true);
			List<User> users = null;
			try {
				users = usersDataProvider.getUsers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (users != null && !users.isEmpty()) {
				for (User user : users) {
					if (user.getUserName().equalsIgnoreCase(username)) {
						error = true;
						errorMessage += "Username " + username + " is already taken! ";
						break;
					}
				}
			}
		}
		
		if (firstName == null || firstName.trim().isEmpty()) {
			error = true;
			errorMessage += "First name is required! ";
		} else {
			firstName = firstName.trim();
			errorMessage += Validations.validateText("First name", firstName, 2, 50, true);
			
		}
		
		if (lastName == null || lastName.trim().isEmpty()) {
			error = true;
			errorMessage += "Last name is required! ";
		} else {
			lastName = lastName.trim();
			errorMessage += Validations.validateText("Last name", lastName, 2, 50, true);
		}
		
		String passwordHashString = "";
		if (password == null || password.isEmpty()) {
			error = true;
			errorMessage += "Password is required! ";
		} else if (!password.equals(password2)) {
			error = true;
			errorMessage += "Passwords don't match! ";
		} else {
			passwordHashString = UserUtils.getSHA256HashString(password);
			if (passwordHashString == null || passwordHashString.isEmpty()) {
				error = true;
				errorMessage += "There is a problem with our application. Please try again later. ";
			}
		}
		
		RequestDispatcher view = null;
		if (!error) {
			User user = new User(null, username, firstName, lastName, passwordHashString, 
					new Date(), UserTypes.USER_TYPE_USER);
			usersDataProvider.saveUser(user);
			request.setAttribute(WebAttributes.SYSTEM_MESSAGE, "You've registered successfully!");
			view = request.getRequestDispatcher(WebPages.LOGIN);
		} else {
			view = request.getRequestDispatcher(WebPages.REGISTER);
			request.setAttribute(WebAttributes.ERROR_MESSAGE, errorMessage);
		}
		view.forward(request, response);
	}

}