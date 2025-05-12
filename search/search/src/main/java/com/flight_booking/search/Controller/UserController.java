package com.flight_booking.search.Controller;

import java.awt.desktop.UserSessionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight_booking.search.Entities.Users;
import com.flight_booking.search.service.userService;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserController {
	@Autowired
	private userService sr;
	
	@PostMapping("/register")
	public Users register(@RequestBody  Users user) {
		return sr.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody Users user) {
		return sr.verify(user);
	}
}
