package model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class MovieWebModel {
	private int id;
	private String title;
	private int hallId;
	private int year;
	private String description;
	private int length;
	private Date projection;
	private String hallName;
	
	public MovieWebModel(Movie movie, String hallName) {
		this.id = movie.getId();
		this.title = movie.getTitle();
		this.hallId = movie.getHallId();
		this.year = movie.getYear();
		this.description = movie.getDescription();
		this.length = movie.getLength();
		this.projection = movie.getProjection();
		this.hallName = hallName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getHallName() {
		return hallName;
	}

	public void setHallName(String hallName) {
		this.hallName = hallName;
	}
	
}
