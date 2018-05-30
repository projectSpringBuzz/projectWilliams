package com.wjma.apachekafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.dto.ServiceTxDTO;

@Service
public class KafkaProducer implements IKafkaProducer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
	
	/**
	 * Bean that allow to create a kafka producer
	 */
	@Autowired
	@Qualifier("kafkaTemplateDetail")
	private KafkaTemplate<String, OrderDTO> kafkaTemplateDetail;
	
	@Autowired
	@Qualifier("kafkaTemplateTx")
	private KafkaTemplate<String, ServiceTxDTO> kafkaTemplateTx;
	
	/**
	 * It's need to definy a kafka's topic for the producer
	 */
	@Value("${kafka.topic.iservice}")
	String kafkaTopicIservice;
	
	@Value("${kafka.topic.oservice}")
	String kafkaTopicOservice;
	
	/**
	 * method that send a object [Details and notes] to kafka
	 */
	public void sendData(OrderDTO data) {
	    log.info("[delivery] data='{}'", data);
	    
	    kafkaTemplateDetail.send(kafkaTopicOservice, data);
	}
	
	/**
	 * method that send a object [Transaction ID] to kafka
	 */
	public void sendTransactions(ServiceTxDTO tx) {
	    log.info("[delivery] tx='{}' '{}'", tx.getPID(), tx.getQID());
	    
	    kafkaTemplateTx.send(kafkaTopicIservice, tx);
	}
}
