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
import model.Movie;
import model.ReservedTicket;
import model.Ticket;
import model.TicketWebModel;
import model.User;

import db.HallDataProvider;
import db.MovieDataProvider;
import db.TicketDataProvider;
import db.UsersDataProvider;

@WebServlet({"/tickets", "/reserve_tickets", "/confirm_tickets", "/check_tickets"})
public class TicketServlet extends HttpServlet {
       
	private HallDataProvider hallDataProvider;
	private TicketDataProvider ticketDataProvider;
	private UsersDataProvider usersDataProvider;
	private MovieDataProvider movieDataProvider;
	private long maxWaitTime;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		hallDataProvider = new HallDataProvider();
		ticketDataProvider = new TicketDataProvider();
		usersDataProvider = new UsersDataProvider();
		movieDataProvider = new MovieDataProvider();
		String expirationTime = getServletContext().getInitParameter("expiration_time");
		Integer expirationTimeMinutes = null;
		try {
			expirationTimeMinutes = Integer.valueOf(expirationTime);
		} catch(NumberFormatException e) {
			throw new ServletException("Init parameter expiration_time has not been initialized!");
		}
		maxWaitTime = expirationTimeMinutes * 60 * 1000;
	}
	
	private synchronized String reserveTickets(String[] seats, User user, Integer movieId) {
		String errorMessage = "";
		if (seats != null && seats.length > 0 && user != null && movieId != null) {
			List<ReservedTicket> reservedTickets = 
					(ArrayList<ReservedTicket>) getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
			if (reservedTickets == null) {
				reservedTickets = new ArrayList<ReservedTicket>();
			}
			System.out.println(seats);
			for (String seat : seats) {
				boolean hasError = false;
				Integer seatId;
				try {
					seatId = Integer.valueOf(seat);
				} catch (NumberFormatException e) {
					seatId = null;
				}
				if (seatId != null) {
					if (reservedTickets != null) {
						for (ReservedTicket reservedTicket : reservedTickets) {
							if (reservedTicket.getSeatId() == seatId) {
								hasError = true;
								errorMessage += "Seat #" + reservedTicket.getSeatId() + " is already taken! ";
								break;
							}
						}
					}
					if (!hasError) {
						reservedTickets.add(new ReservedTicket(user.getId(), movieId, seatId, maxWaitTime));
					}
				}
			}
			getServletContext().setAttribute(WebAttributes.RESERVED_TICKETS, reservedTickets);
		}
		return errorMessage;
	}
	
	private void validateReservedTickets(HttpServletRequest request) {
		List<ReservedTicket> reservedTickets = (ArrayList<ReservedTicket>) 
				request.getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
		List<ReservedTicket> ticketsToRemain = new ArrayList<ReservedTicket>();
		if (reservedTickets != null && !reservedTickets.isEmpty()) {
			for (ReservedTicket ticket : reservedTickets) {
				if (ticket.getExpiryDate().getTime() > new Date().getTime()) {
					ticketsToRemain.add(ticket);
				}
			}
		}
		request.getServletContext().setAttribute(WebAttributes.RESERVED_TICKETS, ticketsToRemain);
	}
	
	private void initializeSeats(HttpServletRequest request) throws ServletException {
		String movie = request.getParameter(WebAttributes.MOVIE_ID);
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
		List<ReservedTicket> reservedTickets = (ArrayList<ReservedTicket>) 
				getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
		System.out.println(reservedTickets);
		if (reservedTickets != null && !reservedTickets.isEmpty()) {
			for (ReservedTicket ticket : reservedTickets) {
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
		request.setAttribute(WebAttributes.MOVIE_ID, movieId);
		request.setAttribute("main_list", mainList);
	}
	
	private void initializeTickets(HttpServletRequest request) throws ServletException {
		String movieIdAsString = request.getParameter(WebAttributes.MOVIE_ID);
		movieIdAsString = movieIdAsString != null ? movieIdAsString.trim() : null;
		Integer movieId;
		try {
			movieId = Integer.valueOf(movieIdAsString);
		} catch (NumberFormatException e) {
			movieId = null;
		}
		if (movieId == null) {
			throw new ServletException("Unknown movie id!");
		}
		
		List<TicketWebModel> movieTickets = new ArrayList<TicketWebModel>();
		
		List<Ticket> tickets = ticketDataProvider.getTickets();
		Movie movie = movieDataProvider.getMovieById(movieId);
		for (Ticket ticket : tickets) {
			if (ticket.getMovieId() == movieId) {
				User user = usersDataProvider.getUserById(ticket.getUserId());
				movieTickets.add(new TicketWebModel(ticket, user.getUserName(), movie.getTitle()));
			}
		}
		if (!tickets.isEmpty()) {
			request.setAttribute(WebAttributes.MOVIE_TICKETS, movieTickets);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		validateReservedTickets(request);
		initializeSeats(request);
		initializeTickets(request);
		request.getRequestDispatcher(WebPages.TICKETS).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(WebAttributes.USER);
		String movie = request.getParameter(WebAttributes.MOVIE_ID);
		movie = movie != null ? movie.trim() : null;
		Integer movieId;
		try {
			movieId = Integer.valueOf(movie);
		} catch (NumberFormatException e) {
			movieId = null;
		}
		String path = request.getServletPath();
		String[] seats = request.getParameterValues("seat");
		String errorMessage = "";
		boolean hasError = false;
		validateReservedTickets(request);
		System.out.println("Path: " + path);
		if (path.equals("/reserve_tickets")) {
			errorMessage += reserveTickets(seats, user, movieId);
		} else if (path.equals("/confirm_tickets")) {
			String[] ticketsToConfirm = request.getParameterValues(WebAttributes.TICKET);
			List<Integer> seatIds = new ArrayList<Integer>();
			if (ticketsToConfirm == null || ticketsToConfirm.length == 0) {
				errorMessage += "You have selected no tickets to confirm! ";
			} else {
				List<ReservedTicket> reservedTickets = 
						(ArrayList<ReservedTicket>) getServletContext().getAttribute(WebAttributes.RESERVED_TICKETS);
				for (String seat : ticketsToConfirm) {
					Integer seatId;
					try {
						seatId = Integer.valueOf(seat);
					} catch (NumberFormatException e) {
						seatId = null;
					}
					if (seatId != null) {
						for (ReservedTicket reservedTicket : reservedTickets) {
							if (reservedTicket.getUserId() == user.getId() && 
									reservedTicket.getMovieId() == movieId &&
									reservedTicket.getExpiryDate().getTime() >= new Date().getTime()) {
								seatIds.add(seatId);
								ticketDataProvider.saveTicket(new Ticket(null, user.getId(), 
										movieId, seatId, new Date(), false));
								break;
							}
						}
						
					}
				}
				if (!seatIds.isEmpty()) {
					if (reservedTickets != null && !reservedTickets.isEmpty()) {
						List<ReservedTicket> newList = new ArrayList<ReservedTicket>();
						for (ReservedTicket ticket : reservedTickets) {
							if (!seatIds.contains(ticket.getSeatId())
									&& ticket.getExpiryDate().getTime() > new Date().getTime()) {
								newList.add(ticket);
							}
						}
						request.getServletContext().setAttribute(WebAttributes.RESERVED_TICKETS, newList);
					}
				}
			}
		} else if (path.equals("/check_tickets")) {
			String[] usedTicketIds = request.getParameterValues("used");
			if (usedTicketIds != null) {
				for (String usedTicketId : usedTicketIds) {
					Integer ticketId;
					try {
						ticketId = Integer.valueOf(usedTicketId);
					} catch (NumberFormatException e) {
						ticketId = null;
					}
					if (ticketId != null) {
						System.out.println("Ticket id: " + ticketId);
						Ticket ticket = ticketDataProvider.getTicketById(ticketId);
						ticket.setUsed(true);
						ticketDataProvider.updateTicket(ticket);
					}
				}
			}
			
		}
		initializeSeats(request);
		initializeTickets(request);
		if (!errorMessage.isEmpty()) {
			request.setAttribute(WebAttributes.ERROR_MESSAGE, errorMessage);
		}
		request.getRequestDispatcher(WebPages.TICKETS).forward(request, response);
	}

}
