package model;

public class HallLayoutWebModel {
	
	private int id;
	private int rowId;
	private int columnId;
	private boolean taken;
	
	public HallLayoutWebModel(HallLayout hl, boolean isTaken) {
		this.id = hl.getId();
		this.rowId = hl.getRow();
		this.columnId = hl.getColumn();
		this.taken = isTaken;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public int getColumnId() {
		return columnId;
	}

	public void setColumnId(int columnId) {
		this.columnId = columnId;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

}