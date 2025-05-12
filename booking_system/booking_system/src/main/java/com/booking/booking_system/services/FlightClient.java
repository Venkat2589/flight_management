package com.booking.booking_system.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.booking.booking_system.Entity.Flight;
import com.booking.booking_system.Entity.Seats;

@FeignClient(name = "FLIGHT")
public interface FlightClient {
	
	@GetMapping("flight/{flightId}")
    Flight getFlightById(@PathVariable Integer flightId);
	
	@PutMapping("/flight/seats")
	public Seats updateSeats(@RequestParam Integer flightId,@RequestParam String seatClass,@RequestParam Integer availableSeats);
}
