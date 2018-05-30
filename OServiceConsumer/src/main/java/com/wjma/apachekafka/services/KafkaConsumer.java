package com.wjma.apachekafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.wjma.apachekafka.business.IUserRRatingService;
import com.wjma.spring.dto.OrderDTO;

@Component
public class KafkaConsumer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	@Autowired
	private IUserRRatingService iUserRRatingService;

	@KafkaListener(topics = "${kafka.topic.oservice}")
	public void processMessage(OrderDTO order) {
		try {
			iUserRRatingService.saveDetail(order);
			logger.info("[received] content detail = '{}' '{}'", order.getDetails().size(), order.getNotes().size());
		} catch (Exception e) {
			logger.error("Error al guardar order" ,e);
		}
	}
}
