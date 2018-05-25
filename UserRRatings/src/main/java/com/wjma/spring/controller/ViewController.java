package com.wjma.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class ViewController {

	private static Logger logger = LoggerFactory.getLogger(ViewController.class);

	@Value("${welcome.message}")
	private String message;

	@RequestMapping(value = "/")
	public String index(Model model) {
		model.addAttribute("message", this.message);
		logger.info("Hello World!");
		return "index";
	}
}
