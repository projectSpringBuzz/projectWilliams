package com.wjma.spring.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO implements Serializable {

	private static final long serialVersionUID = -4660472384100654897L;

	private Integer idphonenumber;
	private String phonenumber;
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Integer getIdphonenumber() {
		return idphonenumber;
	}
	public void setIdphonenumber(Integer idphonenumber) {
		this.idphonenumber = idphonenumber;
	}
	
}