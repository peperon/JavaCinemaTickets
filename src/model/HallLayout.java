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
@Table(name="hall_layout")
public class HallLayout implements Serializable {
	
	@Id
	@SequenceGenerator(name="hall_layout_sequence", sequenceName="hall_layout_id_seq", allocationSize=1)  
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="hall_layout_sequence")  
	@Basic
	@Column(name="id")
	private int id;
	@Basic
	@Column(name="row_number")
	private int row;
	@Basic
	@Column(name="column_number")
	private int column;
	
	public HallLayout() { }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}

	public void setRowId(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumnId(int column) {
		this.column = column;
	};
}