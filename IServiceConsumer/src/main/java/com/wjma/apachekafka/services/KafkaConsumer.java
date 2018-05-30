package com.wjma.apachekafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wjma.spring.dto.ServiceTxDTO;

@Component
public class KafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
	
	@KafkaListener(topics="${kafka.topic.iservice}")
    public void processMessage(ServiceTxDTO tx) {
		logger.info("[received] content = '{}' '{}'", tx.getPID(), tx.getQID());
    }
}
