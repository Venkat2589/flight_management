package com.booking.booking_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.Passenger;
import com.booking.booking_system.Exception.bookingIdNotFound;
import com.booking.booking_system.repo.Booking_repo;
import com.booking.booking_system.services.service;


@RestController
@RequestMapping("/book")
public class controller {
	
	@Autowired
	private service sr;
	

	
	@PostMapping("/booking/{noOfSeats}/{seatClass}/{flightId}")
	public String book(@PathVariable Integer noOfSeats,@PathVariable String seatClass,@PathVariable Integer flightId,@RequestBody Booking book) {
		
		return sr.book(noOfSeats, seatClass, flightId, book);
	}
	
	@GetMapping("/getBookings")
	public List<Booking> getBookings(){
		return sr.getAllBookings();
	}
	
	@GetMapping("/booking/user/{userId}")
	public Booking getBookingByUserId(@PathVariable Integer userId) {
		return (Booking) sr.getBookingByUserId(userId);
	}
	
	@DeleteMapping("/cancel/passenger/{passengerId}")
	public int cancelByPassenger(@PathVariable Integer passengerId) {
		return sr.cancelByPassengerId(passengerId);
	}
	
	@DeleteMapping("/cancel/booking/{bookingId}")
	public int cancelByBookingId(@PathVariable Integer bookingId)  {
		return sr.cancelByBookingId(bookingId);
	}
	
	@GetMapping("/booking/passenger/{passenger_id}")
	public Passenger getPassenger(@PathVariable Integer passenger_id) {
		return sr.getPassenger(passenger_id);
	}
}
