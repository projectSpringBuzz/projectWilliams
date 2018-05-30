package com.wjma.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.wjma.spring.dto.OrderDTO;
import com.wjma.spring.services.IUserRRatingService;

@Controller
@CrossOrigin
public class ViewController {

	private static Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Value("${welcome.message}")
	private String message;
	
	@Autowired
	private IUserRRatingService iUserRRatingService;

	@GetMapping("/details/{phoneNumber}")
	public String findByPhoneNumber(Model model,
			@PathVariable("phoneNumber") String phoneNumber,
			HttpSession session) {

		if(phoneNumber == null || phoneNumber.isEmpty()) {
			model.addAttribute("error", "Param phoneNumber not found");
		}
		
		List<OrderDTO> list = iUserRRatingService.findOrdersListByPhoneNumber(phoneNumber);
		model.addAttribute("list", list);
		model.addAttribute("phoneNumber", phoneNumber);
		
		session.setAttribute("phoneNumber", phoneNumber);
		session.setAttribute("size", list.size());
		
		return "details";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	
	@GetMapping("/login")
	public String login(Model model, @RequestParam(value="error", required=false) String error) {
		if("true".equalsIgnoreCase(error)) {
			model.addAttribute("error", true);
		}
		return "login";
	}
	
	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("message", this.message);
		logger.info("Registration!...");
		return "registration";
	}
	
	@GetMapping("/error/access-denied")
    public String accessDenied() {
        return "accessDenied";
    }
}
