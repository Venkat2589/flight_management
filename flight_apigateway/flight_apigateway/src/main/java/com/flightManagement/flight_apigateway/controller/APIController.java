package com.flightManagement.flight_apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("actuator/info")
public class APIController {

	@GetMapping
	public String s()
	{
		return "This is gateway";
	}
}
