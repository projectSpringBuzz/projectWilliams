package com.wjma.spring.dto;

import java.io.Serializable;

public class NoteDTO implements Serializable {

	private static final long serialVersionUID = -3296688979924672138L;

	private int id;
	private int orderID;
	private String notes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
