package controllers;

import java.sql.SQLException;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.UserTypes;
import utils.UserUtils;
import utils.WebAttributes;
import utils.WebPages;

import model.User;

import db.UsersDataProvider;

/**
 * Servlet implementation class UsersServlet
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private UsersDataProvider userProvider;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userProvider = new UsersDataProvider();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users;
		try {
			users = userProvider.getRegularUsers();
		} catch(SQLException ex){
			users = null;
		}
		if (!UserUtils.isUserInRole(request, UserTypes.USER_TYPE_POWER_USER)) {
			request.setAttribute(WebAttributes.ERROR_MESSAGE, 
					"You are not authorized to access this resource! ");
			request.getRequestDispatcher(WebPages.HOME).forward(request, response);
			return;
		}
		request.setAttribute(WebAttributes.USERS, users);
		request.getRequestDispatcher(WebPages.USERS).forward(request, response);
	}
}
