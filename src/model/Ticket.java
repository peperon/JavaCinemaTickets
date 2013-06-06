package model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date")
	private Date date;
	@Basic
	@Column(name="used")
	private boolean used;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="user_id", referencedColumnName="id")
	private User user;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="movie_id", referencedColumnName="id")
	private Movie movie;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="seat_id", referencedColumnName="id")
	private HallLayout seat;
	
	public Ticket() { }

	public Ticket(Integer id, int userId, int movieId, int seatId, Date date, boolean used) {
		super();
		this.id = id;
		
		user = new User();
		user.setId(userId);
		
		movie = new Movie();
		movie.setId(movieId);
		
		seat = new HallLayout();
		seat.setId(seatId);
		
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
		return user.getId();
	}

	public int getMovieId() {
		return getMovie().getId();
	}

	public int getSeatId() {
		return getSeat().getId();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public HallLayout getSeat() {
		return seat;
	}

	public void setSeat(HallLayout seat) {
		this.seat = seat;
	}
	
}