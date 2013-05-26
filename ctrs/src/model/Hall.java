package model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="hall")
public class Hall implements Serializable  {
	@Id
	@SequenceGenerator(name="hall_sequence", sequenceName="hall_id_seq", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="hall_sequence")  
	@Basic
	@Column(name="id")
	private Integer id;
	@Basic
	@Column(name="name")
	private String name;
	
	public Hall() { }
	
	public Hall(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}