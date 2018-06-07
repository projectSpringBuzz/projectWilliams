package com.wjma.spring.dto;

import java.io.Serializable;
import java.util.List;

public class MessageDTO implements Serializable{

	private static final long serialVersionUID = -3303097195291153145L;
	
	private String status;
	private Integer size;
	private List<OrderDTO> list;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<OrderDTO> getList() {
		return list;
	}
	public void setList(List<OrderDTO> list) {
		this.list = list;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

}
