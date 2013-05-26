package controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Hall;
import model.Movie;
import model.MovieWebModel;
import model.User;

import utils.UserTypes;
import utils.Validations;
import utils.WebAttributes;
import utils.WebPages;

import db.HallDataProvider;
import db.MovieDataProvider;

@WebServlet({"/movie", "/movie/", "/movies", "/movies/"})
public class MovieServlet extends HttpServlet {
	
	private MovieDataProvider movieDataProvider;
	private HallDataProvider hallDataProvider;
	
	public void init(ServletConfig config) throws ServletException {
		movieDataProvider = new MovieDataProvider();
		hallDataProvider = new HallDataProvider();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute(WebAttributes.USER);
		
		if (user == null) {
			request.getRequestDispatcher(WebPages.LOGIN).forward(request, response);
			return;
		}
		String path = request.getServletPath();
		System.out.println("Path in MovieServlet: " + path);
		RequestDispatcher view = null;
		if (path.equals("/movie") || path.equals("/movie/")) {
			String id = request.getParameter(WebAttributes.ID);
			if (id == null || id.isEmpty()) { // editing an existing movie
				id = (String) request.getAttribute(WebAttributes.ID);
			}
			if (id != null && !id.isEmpty()) {
				int movieId = Integer.parseInt(id);
				Movie movie = movieDataProvider.getMovieById(movieId);
				request.setAttribute(WebAttributes.MOVIE, movie);
			}
			List<Hall> halls = hallDataProvider.getHalls();
			request.setAttribute(WebAttributes.HALLS, halls);
			view = request.getRequestDispatcher(WebPages.MOVIE_EDIT);
		} else if (path.equals("/movies") || path.equals("/movies/")) {
			List<Movie> movies = movieDataProvider.getMovies(user.getUserTypeId() == UserTypes.USER_TYPE_USER);
			List<MovieWebModel> listOfMovies = new ArrayList<MovieWebModel>();
			for (Movie movie : movies) {
				String hallName = hallDataProvider.getHallById(movie.getHallId()).getName();
				listOfMovies.add(new MovieWebModel(movie, hallName));
			}
			request.setAttribute(WebAttributes.MOVIES, listOfMovies);
			view = request.getRequestDispatcher(WebPages.MOVIES_LIST);
		} else {
			view = request.getRequestDispatcher(WebPages.HOME);
		}
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String hall = request.getParameter("hall");
		String year = request.getParameter("year");
		String description = request.getParameter("description");
		String length = request.getParameter("length");
		String projectionDate = request.getParameter("projection_date");
		String projectionTime = request.getParameter("projection_time");
		
		year = year != null ? year.trim() : null;
		description = description != null ? description.trim() : null;
		length = length != null ? length.trim() : null;
		projectionDate = projectionDate != null ? projectionDate.trim() : null;
		projectionTime = projectionTime != null ? projectionTime.trim() : null;
		
		String errorMessage = "";
		
		Integer movieId = null;
		try {
			movieId = Integer.valueOf(id);
		} catch(NumberFormatException e) {
			movieId = null;
		}
		
		Integer hallId = null;
		if (hall == null || hall.isEmpty() || hall.equals("-")) {
			errorMessage += "Hall is a required field! ";
		} else {
			hallId = Integer.valueOf(hall);
		}
		
		title = title != null ? title.trim() : null;
		errorMessage += Validations.validateText("Movie title", title, 1, 100, true);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		dateFormat.setLenient(false);
		timeFormat.setLenient(false);
		Date date = null;
		Date time = null;
		Date dateTime = null;
		try {
			date = dateFormat.parse(projectionDate);
		} catch (ParseException e) {
			errorMessage += "Projection date should be in dd.MM.yyyy format! ";
		}
		try {
			time = timeFormat.parse(projectionTime);
		} catch (ParseException e) {
			errorMessage += "Projection time should be in HH:mm format! ";
		}
		if (date != null && time != null) {
			SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
			dateTimeFormat.setLenient(false);
			try {
				dateTime = dateTimeFormat.parse(projectionDate + " " + projectionTime);
			} catch (ParseException e) {
				errorMessage += "Incorrect date or time! ";
			}
		}
		
		Calendar calendar = new GregorianCalendar();
		int currentYear = calendar.get(Calendar.YEAR);
		Integer movieYear = null;
		try {
			movieYear = Integer.valueOf(year);
		} catch(NumberFormatException e) {
			errorMessage += "Incorrect movie year! ";
		}
		if (movieYear != null && movieYear > currentYear + 1) {
			errorMessage += "Movie year cannot be greater than " + (currentYear + 1) + "! ";
		}
		errorMessage += Validations.validateText("Description", description, 0, 1000, false);
		
		Integer movieLength = null;
		try {
			movieLength = Integer.valueOf(length);
		} catch(NumberFormatException e) {
			errorMessage += "Incorrect movie length! ";
		}
		if (errorMessage.isEmpty()) {
			if (movieId == null) { // new movie
				Movie movie = new Movie(null, title, hallId, movieYear, description, movieLength, dateTime);
				Movie insertedMovie = movieDataProvider.saveMovie(movie);
				request.setAttribute(WebAttributes.MOVIE, insertedMovie);
			} else {
				Movie movie = new Movie(movieId, title, hallId, movieYear, description, movieLength, dateTime);
				movieDataProvider.updateMovie(movie);
				request.setAttribute(WebAttributes.MOVIE, movie);
			}
			request.setAttribute(WebAttributes.HALLS, hallDataProvider.getHalls());
		} else {
			request.setAttribute(WebAttributes.ERROR_MESSAGE, errorMessage);
		}
		System.out.println("Error msg: " + errorMessage);
		request.getRequestDispatcher(WebPages.MOVIE_EDIT).forward(request, response);
	}

}