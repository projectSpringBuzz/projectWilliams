package com.wjma.spring.dto;

import java.io.Serializable;

public class NoteDTO implements Serializable {

	private static final long serialVersionUID = -3296688979924672138L;

	private int id;
	private String notes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
