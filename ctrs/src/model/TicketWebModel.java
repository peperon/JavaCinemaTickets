package model;

import java.util.Date;

public class TicketWebModel {
	
	private int userId;
	private int movieId;
	private int seatId;
	private Date expiryDate;
	
	public TicketWebModel(int userId, int movieId, int seatId) {
		this.userId = userId;
		this.movieId = movieId;
		this.seatId = seatId;
		this.expiryDate = new Date(new Date().getTime() + 1 * 60 * 1000);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
}
