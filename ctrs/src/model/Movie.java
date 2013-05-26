package model;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name="movie")
public class Movie implements Serializable {
	
	@Id
	@SequenceGenerator(name="movie_sequence", sequenceName="movie_id_seq", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="movie_sequence")  
	@Basic
	@Column(name="id")
	private Integer id;
	@Basic
	@Column(name="title")
	private String title;
	@Basic
	@Column(name="hall_id")
	private int hallId;
	@Basic
	@Column(name="year")
	private int year;
	@Basic
	@Column(name="description")
	private String description;
	@Basic
	@Column(name="length")
	private int length;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="projection")
	private Date projection;
	
	public Movie() { }
	
	public Movie(Integer id, String title, int hallId, int year,
			String description, int length, Date projection) {
		super();
		this.id = id;
		this.title = title;
		this.hallId = hallId;
		this.year = year;
		this.description = description;
		this.length = length;
		this.projection = projection;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public Date getProjection() {
		return projection;
	}

	public void setProjection(Date projection) {
		this.projection = projection;
	}
	
}
