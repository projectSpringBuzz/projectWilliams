package com.wjma.spring.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v0/order")
public class RestFulController {

	@RequestMapping(value="/phoneNumber/{phoneNumber}")
    String hello(@PathVariable("phoneNumber") String phoneNumber) {
        return "Hello World!";
    }
}
