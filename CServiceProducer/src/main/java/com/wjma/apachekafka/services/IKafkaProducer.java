package com.wjma.apachekafka.services;

import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.dto.ServiceTxDTO;

public interface IKafkaProducer {

	public void sendTransactions(ServiceTxDTO tx);
	public void sendData(OrderDTO order);
}
