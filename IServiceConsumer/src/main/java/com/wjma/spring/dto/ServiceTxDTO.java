package com.wjma.spring.dto;

import java.io.Serializable;

public class ServiceTxDTO implements Serializable {

	private static final long serialVersionUID = 4933243780073463059L;

	private Integer QID;
	private Integer PID;
	
	public Integer getPID() {
		return PID;
	}
	public void setPID(Integer pID) {
		PID = pID;
	}
	public Integer getQID() {
		return QID;
	}
	public void setQID(Integer qID) {
		QID = qID;
	}
}
