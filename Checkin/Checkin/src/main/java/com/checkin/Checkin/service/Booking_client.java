package com.checkin.Checkin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.checkin.Checkin.Entity.Passenger;

@FeignClient(name = "BOOKING-SYSTEM")
public interface Booking_client {
	
	@GetMapping("/book/book/{passenger_id}")
	public Passenger getPassenger(@PathVariable Integer passenger_id);
}
