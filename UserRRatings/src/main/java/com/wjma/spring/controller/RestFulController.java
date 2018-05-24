package com.wjma.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wjma.spring.dto.DetailDTO;
import com.wjma.spring.dto.NoteDTO;

@RestController
@CrossOrigin
@RequestMapping(value="/v0/order")
public class RestFulController {

	@RequestMapping(value="/phoneNumber/{phoneNumber}")
	ResponseEntity<DetailDTO> hello(@PathVariable("phoneNumber") String phoneNumber) {
		DetailDTO detail = new DetailDTO();
		detail.setOrderID(1);
		detail.setProductName("product");
		detail.setRating(4);
		
		NoteDTO note1 = new NoteDTO();
		note1.setId(1);
		note1.setNotes("bla bla");
		note1.setOrderID(detail.getOrderID());
		
		detail.addNote(note1);
		return new ResponseEntity<DetailDTO>(detail, HttpStatus.OK);
    }
}
