package com.wjma.spring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = -4660472384100654897L;

	private Integer orderID;
	private List<DetailDTO> details;
	private List<NoteDTO> notes;
	
	public List<DetailDTO> getDetails() {
		if(details == null){
			details = new ArrayList<DetailDTO>();
		}
		return details;
	}
	public void setDetails(List<DetailDTO> detail) {
		this.details = detail;
	}
	public List<NoteDTO> getNotes() {
		if(notes == null){
			notes = new ArrayList<NoteDTO>();
		}
		return notes;
	}
	public void setNotes(List<NoteDTO> noteDTO) {
		this.notes = noteDTO;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
}