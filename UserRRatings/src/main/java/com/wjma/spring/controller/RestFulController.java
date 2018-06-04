package com.wjma.spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.services.IUserRRatingService;

@RestController
@CrossOrigin
@RequestMapping("v0")
public class RestFulController {

	private static Logger logger = LoggerFactory.getLogger(RestFulController.class);

	@Autowired
	private IUserRRatingService iUserRRatingService;

	@PostMapping(value="detail")
	ResponseEntity<?> saveDetail(@RequestBody OrderDTO order) {
		try {
			if(order.getIdphonenumber() != null){
				iUserRRatingService.saveDetail(order);
				iUserRRatingService.insertPhoneNumberOrderId(order.getIdphonenumber(), order.getPhonenumber());
			} else {
				iUserRRatingService.saveDetail(order);
			}
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR call service save detail",e);
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PostMapping(value="detail/{orderID}/rating/{rating}")
	ResponseEntity<?> saveDetailRating(@PathVariable("orderID") int orderID,
			@PathVariable("rating") int rating,
			@RequestParam("productName") String productName) {
		try {
			if(productName == null || rating <=0 || rating > 5 || orderID <= 0) {
				throw new Exception("Params invalid");
			}
			iUserRRatingService.updateRatingProduct(orderID, productName,rating);
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR call service update rating",e);
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value = "detail/phoneNumber/{phoneNumber}")
	ResponseEntity<List<OrderDTO>> findByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		try {
			return new ResponseEntity<List<OrderDTO>>(iUserRRatingService.findOrdersListByPhoneNumber(phoneNumber),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ERROR call service search detail", e);
			return new ResponseEntity<List<OrderDTO>>(new ArrayList<OrderDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
