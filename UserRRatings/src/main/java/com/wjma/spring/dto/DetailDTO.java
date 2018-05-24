package com.wjma.spring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailDTO implements Serializable {

	private static final long serialVersionUID = -8628132197813378375L;

	private int orderID;
	private String productName;
	private int rating;
	private List<NoteDTO> notes;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public List<NoteDTO> getNotes() {
		if (notes == null) {
			notes = new ArrayList<NoteDTO>();
		}
		return notes;
	}

	public void addNote(NoteDTO note) {
		if (note != null) {
			getNotes().add(note);
		}
	}
}
