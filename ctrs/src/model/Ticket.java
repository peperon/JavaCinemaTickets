package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ticket")
public class Ticket implements Serializable {
	
	@Id
	@SequenceGenerator(name="ticket_sequence", sequenceName="ticket_id_seq", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="ticket_sequence")  
	@Basic
	@Column(name="id")
	private Integer id;
	@Basic
	@Column(name="user_id")
	private int userId;
	@Basic
	@Column(name="movie_id")
	private int movieId;
	@Basic
	@Column(name="seat_id")
	private int seatId;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	@Basic
	@Column(name="used")
	private boolean used;
	
	public Ticket() { }

	public Ticket(Integer id, int userId, int movieId, int seatId, Date date, boolean used) {
		super();
		this.id = id;
		this.userId = userId;
		this.movieId = movieId;
		this.seatId = seatId;
		this.date = date;
		this.used = used;
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