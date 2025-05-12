package com.checkin.Checkin.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.checkin.Checkin.service.serviceImpl;

@RestController
@RequestMapping("/checkin")
public class checkIn_controller {
	
	private serviceImpl sr;
	
	public checkIn_controller(serviceImpl sr) {
		this.sr=sr;
	}
	
	@GetMapping("/{passenger_id}")
	public String checkIn(@PathVariable Integer passenger_id) {
		return sr.checkIn(passenger_id);
	}
}
