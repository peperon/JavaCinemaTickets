package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.WebAttributes;
import utils.WebPages;

import model.Ticket;

import db.TicketDataProvider;

/**
 * Servlet implementation class TicketsPerUserServlet
 */
@WebServlet("/user_tickets")
public class UserTicketsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TicketDataProvider ticketDataProvider;
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ticketDataProvider = new TicketDataProvider();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdString = request.getParameter("user_id");
		userIdString = userIdString != null ? userIdString.trim() : null;
		Integer userId;
		try {
			userId = Integer.valueOf(userIdString);
		} catch (NumberFormatException e) {
			userId = null;
		}
		if (userId == null) {
			throw new ServletException("Unknown movie id!");
		}
		
		List<Ticket> tickets = ticketDataProvider.getTicketsByUser(userId);
		
		request.setAttribute(WebAttributes.USER_TICKETS, tickets);
		request.getRequestDispatcher(WebPages.USER_TICKETS).forward(request, response);
	}

}
