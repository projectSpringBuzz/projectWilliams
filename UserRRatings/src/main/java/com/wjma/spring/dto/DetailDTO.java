package com.wjma.spring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailDTO implements Serializable {

	private static final long serialVersionUID = -8628132197813378375L;

	private int orderID;
	private List<ProductDTO> products;
	private List<NoteDTO> notes;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
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

	public List<ProductDTO> getProducts() {
		if(products == null){
			products = new ArrayList<ProductDTO>();
		}
		return products;
	}
	
	public void addProduct(ProductDTO product) {
		if (product != null) {
			getProducts().add(product);
		}
	}
}
