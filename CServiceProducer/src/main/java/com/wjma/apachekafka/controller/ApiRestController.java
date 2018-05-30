package com.wjma.apachekafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjma.apachekafka.services.IKafkaProducer;
import com.wjma.spring.dto.CServiceKafkaDTO;

/**
* API REST that allow send data to kafka
*/
@RestController
@RequestMapping("/in/kafka")
public class ApiRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiRestController.class);
	
	@Autowired
	private IKafkaProducer producer;
	
	@PostMapping("producer")
	public ResponseEntity<?> producer(@RequestBody CServiceKafkaDTO inKafka){
		ResponseEntity<?> rpta = null;
		try{
			logger.info("[producer] IN: "+inKafka.getTx().getPID() + " | "+inKafka.getTx().getQID() + " | Order details size: "+inKafka.getOrder().getDetails().size());
			producer.sendData(inKafka.getOrder());
			producer.sendTransactions(inKafka.getTx());
			rpta = new ResponseEntity<String>("OK",HttpStatus.OK);
		}catch(Exception e){
			logger.error("Error service: ",e);
			rpta = new ResponseEntity<String>("ERROR",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return rpta;
	}
	
	@GetMapping("status")
	public ResponseEntity<?> status(){
		return new ResponseEntity<String>("OK",HttpStatus.OK);
	}
}
