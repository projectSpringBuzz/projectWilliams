package com.wjma.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.wjma.spring.dto.NoteDTO;
import com.wjma.spring.dto.ProductDTO;

@RestController
@CrossOrigin
@RequestMapping("v0")
public class RestFulController {
	
	private static Logger logger = LoggerFactory.getLogger(RestFulController.class);

	@PostMapping(value="detail",produces="application/json")
	ResponseEntity<?> saveDetail(@RequestBody DetailDTO detail) {
		logger.info("DetailDTO: {} {}", detail.getOrderID(), detail.getProducts().size());
		return new ResponseEntity<String>("", HttpStatus.OK);
    }
	
	@GetMapping(value="detail/phoneNumber/{phoneNumber}")
	ResponseEntity<DetailDTO> findByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
		DetailDTO detail = new DetailDTO();
		detail.setOrderID(1);
		
		ProductDTO p1 = new ProductDTO();
		p1.setProductName("product 1");
		p1.setRating(4);
		
		ProductDTO p2 = new ProductDTO();
		p2.setProductName("product 2");
		p2.setRating(5);
		
		NoteDTO note1 = new NoteDTO();
		note1.setId(1);
		note1.setNotes("bla bla");
		note1.setOrderID(detail.getOrderID());
		
		NoteDTO note2 = new NoteDTO();
		note2.setId(2);
		note2.setNotes("ble ble");
		note2.setOrderID(detail.getOrderID());
		
		detail.addProduct(p1);
		detail.addProduct(p2);
		detail.addNote(note1);
		detail.addNote(note2);
		return new ResponseEntity<DetailDTO>(detail, HttpStatus.OK);
    }
}
