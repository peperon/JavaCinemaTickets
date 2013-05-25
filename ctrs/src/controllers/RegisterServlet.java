package controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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

import db.BaseDataProvider;
import db.UsersDataProvider;

import utils.DatabaseService;
import utils.LogUtils;
import utils.UserUtils;
import utils.WebAttributes;
import utils.WebPages;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
    private UsersDataProvider dataProvider;
    
    @Override
    public void init() throws ServletException {
    	dataProvider = new UsersDataProvider();
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogUtils.logPath(request);
		RequestDispatcher view = request.getRequestDispatcher(WebPages.REGISTER);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			List<User> users = null;
			try {
				users = dataProvider.getUsers();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (users != null && !users.isEmpty()) {
				for (User user : users) {
					if (user.getUserName().equalsIgnoreCase(username)) {
						error = true;
						errorMessage += " Username " + username + " is already taken! ";
						break;
					}
				}
			}
		}
		
		if (firstName == null || firstName.isEmpty()) {
			error = true;
			errorMessage += "First name is required! ";
		} else {
			firstName = firstName.trim();
		}
		
		if (lastName == null || lastName.isEmpty()) {
			error = true;
			errorMessage += "Last name is required! ";
		} else {
			lastName = lastName.trim();
		}
		
		String passwordHashString = "";
		if (password == null || password.isEmpty()) {
			error = true;
			errorMessage += "Password is required! ";
		} else if (!password.equals(password2)) {
			error = true;
			errorMessage += "Passwords don't match!";
		} else {
			try {
				passwordHashString = UserUtils.getSHA256HashString(password);
			} catch (NoSuchAlgorithmException e) {
				error = true;
				errorMessage += "There is a problem with our application. Please try again later. ";
				e.printStackTrace();
			}
		}
		
		RequestDispatcher view = null;
		if (!error && !passwordHashString.isEmpty()) {
			User user = new User(null, username, firstName, lastName, passwordHashString, new Date());
			dataProvider.saveUser(user);
			request.setAttribute(WebAttributes.SYSTEM_MESSAGE, "You've registered successfully!");
			view = request.getRequestDispatcher(WebPages.LOGIN);
		} else {
			view = request.getRequestDispatcher(WebPages.REGISTER);
			request.setAttribute(WebAttributes.ERROR_MESSASAGE, errorMessage);
		}
		view.forward(request, response);
	}

}