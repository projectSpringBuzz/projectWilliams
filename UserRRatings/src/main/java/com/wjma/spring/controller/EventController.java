package com.wjma.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.wjma.spring.dto.MessageDTO;
import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.services.IUserRRatingService;

@Controller
@RequestMapping("v0")
public class EventController {

	@Autowired
	private IUserRRatingService iUserRRatingService;
	
	@GetMapping(value = "detail/polling/phoneNumber")
	SseEmitter findByPhoneNumberEventStream(HttpSession session) throws IOException {
		String phoneNumber = (String) session.getAttribute("phoneNumber");
		Integer sizeList = (Integer) session.getAttribute("size");
		
		SseEmitter emitter = new SseEmitter();
		MessageDTO message = new MessageDTO();
		message.setStatus("ND");
		
		List<OrderDTO> list = iUserRRatingService.findOrdersListByPhoneNumberFlux(phoneNumber);		
		
		if(sizeList == list.size()){
			emitter.send(message);
			emitter.complete();
			return emitter;
		}
		
		session.setAttribute("size", list.size());
		
		message.setStatus("OK");
		message.setList(list);
		message.setSize(list.size());
		
		emitter.send(message);
		emitter.complete();
		return emitter;
	}
}