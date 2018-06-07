package com.wjma.spring.dto;

import java.io.Serializable;

public class CServiceKafkaDTO implements Serializable{

	private static final long serialVersionUID = 4531904311926202690L;
	
	private ServiceTxDTO tx;
	private OrderDTO order;
	
	public ServiceTxDTO getTx() {
		return tx;
	}
	public void setTx(ServiceTxDTO tx) {
		this.tx = tx;
	}
	public OrderDTO getOrder() {
		return order;
	}
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
}
