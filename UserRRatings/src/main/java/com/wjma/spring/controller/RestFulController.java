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
import org.springframework.web.bind.annotation.RestController;

import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.services.IUserRRatingService;

@RestController
@CrossOrigin
@RequestMapping("v0")
public class RestFulController {

	private static Logger logger = LoggerFactory.getLogger(RestFulController.class);

	@Autowired
	private IUserRRatingService iUserRRatingService;

	@PostMapping(value="detail",produces="application/json")
	ResponseEntity<?> saveDetail(@RequestBody DetailDTO detail) {
		try {
			iUserRRatingService.saveDetail(detail);
			return new ResponseEntity<String>("", HttpStatus.OK);
		}catch(Exception e) {
			logger.error("ERROR call service save detail",e);
			return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

	@GetMapping(value = "detail/phoneNumber/{phoneNumber}")
	ResponseEntity<List<DetailDTO>> findByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		try {
			return new ResponseEntity<List<DetailDTO>>(iUserRRatingService.findDetailsByPhoneNumber(phoneNumber),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ERROR call service search detail", e);
			return new ResponseEntity<List<DetailDTO>>(new ArrayList<DetailDTO>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
