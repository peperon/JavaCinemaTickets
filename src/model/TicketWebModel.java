package model;

import java.util.Date;

public class TicketWebModel {
	
	private Integer id;
	private int userId;
	private String userName;
	private int movieId;
	private String movieName;
	private int seatId;
	private Date date;
	private boolean used;
	
	public TicketWebModel(Ticket ticket, String userName, String movieName) {
		this.id = ticket.getId();
		this.userId = ticket.getUserId();
		this.userName = userName;
		this.movieId = ticket.getMovieId();
		this.movieName = movieName;
		this.seatId = ticket.getSeatId();
		this.date = ticket.getDate();
		this.used = ticket.isUsed();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

}