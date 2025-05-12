package com.booking.booking_system.services;

import java.util.List;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.Passenger;
import com.booking.booking_system.Exception.bookingIdNotFound;
import com.booking.booking_system.Exception.passengerNotfound;

public interface service {

	
	public Booking getBookingByUserId(Integer userId);
	public String book(Integer noOfSeats,String seatClass,Integer flightId ,Booking book);
	
	public int cancelByPassengerId(Integer passengerId) throws passengerNotfound;
	public Passenger getPassenger(Integer passenger_id);
	public List<Booking> getAllBookings();
	int cancelByBookingId(Integer userId) throws bookingIdNotFound;

}
