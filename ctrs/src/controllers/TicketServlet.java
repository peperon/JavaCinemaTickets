package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.WebAttributes;
import utils.WebPages;

import model.HallLayout;
import model.HallLayoutWebModel;
import model.Ticket;
import model.TicketWebModel;
import model.User;

import db.HallDataProvider;
import db.TicketDataProvider;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {
       
	private HallDataProvider hallDataProvider;
	private TicketDataProvider ticketDataProvider;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		hallDataProvider = new HallDataProvider();
		ticketDataProvider = new TicketDataProvider();
	}
	
	private void initializeSeats(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String movie = request.getParameter("movie_id");
		movie = movie != null ? movie.trim() : null;
		Integer movieId;
		try {
			movieId = Integer.valueOf(movie);
		} catch (NumberFormatException e) {
			movieId = null;
		}
		if (movieId == null) {
			throw new ServletException("Unknown movie id!");
		}
		
		List<Integer> takenSeats = new ArrayList<Integer>();
		ArrayList<TicketWebModel> reservedTickets = (ArrayList<TicketWebModel>) 
				getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
		System.out.println(reservedTickets);
		if (reservedTickets != null && !reservedTickets.isEmpty()) {
			for (TicketWebModel ticket : reservedTickets) {
				if (ticket.getMovieId() == movieId && ticket.getExpiryDate().getTime() > new Date().getTime()) {
					takenSeats.add(ticket.getSeatId());
				}
			}
		}
		
		List<Ticket> soldTickets = ticketDataProvider.getTickets();
		for (Ticket ticket : soldTickets) {
			if (ticket.getMovieId() == movieId) {
				takenSeats.add(ticket.getSeatId());
			}
		}
		
		List<HallLayout> layout = hallDataProvider.getHallLayout();
		List<HallLayoutWebModel> rowList = new ArrayList<HallLayoutWebModel>();
		List<List<HallLayoutWebModel>> mainList = new ArrayList<List<HallLayoutWebModel>>();
		int currentRow = layout.get(0).getRow();
		for (HallLayout hl : layout) {
			HallLayoutWebModel webModel = new HallLayoutWebModel(hl, false);
			if (takenSeats.contains(hl.getId())) {
				webModel.setTaken(true);
			}
			if (hl.getRow() == currentRow) {
				rowList.add(webModel);
			} else {
				currentRow = hl.getRow();
				mainList.add(rowList);
				rowList = new ArrayList<HallLayoutWebModel>();
				rowList.add(webModel);
			}
		}
		mainList.add(rowList);
		request.setAttribute("movie_id", movieId);
		request.setAttribute("main_list", mainList);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initializeSeats(request, response);
		request.getRequestDispatcher(WebPages.TICKETS).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] seats = request.getParameterValues("seat");
		User user = (User) request.getSession().getAttribute(WebAttributes.USER);
		String movie = request.getParameter("movie_id");
		movie = movie != null ? movie.trim() : null;
		Integer movieId;
		try {
			movieId = Integer.valueOf(movie);
		} catch (NumberFormatException e) {
			movieId = null;
		}
		if (seats != null && seats.length > 0 && user != null && movieId != null) {
			ArrayList<TicketWebModel> reservedTickets = 
					(ArrayList<TicketWebModel>) getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
			if (reservedTickets == null) {
				reservedTickets = new ArrayList<TicketWebModel>();
			}
			for (String seat : seats) {
				Integer seatId;
				try {
					seatId = Integer.valueOf(seat);
				} catch (NumberFormatException e) {
					seatId = null;
				}
				if (seatId != null) {
					reservedTickets.add(new TicketWebModel(user.getId(), movieId, seatId));
				}
			}
			getServletContext().setAttribute(WebAttributes.RESERVED_TICKETS, reservedTickets);
		}
		initializeSeats(request, response);
		request.getRequestDispatcher(WebPages.TICKETS).forward(request, response);
	}

}
