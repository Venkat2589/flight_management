package com.booking.booking_system;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.Flight;
import com.booking.booking_system.Entity.Passenger;
import com.booking.booking_system.Entity.Seats;
import com.booking.booking_system.Exception.flightNotFound;
import com.booking.booking_system.repo.Booking_repo;
import com.booking.booking_system.services.FlightClient;
import com.booking.booking_system.services.serviceImpl;

import jakarta.validation.constraints.AssertTrue;

public class service_test {
	@Mock
	private Booking_repo br;
	
	@Mock
	private FlightClient fc;
	
	@InjectMocks
	private serviceImpl sr;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void testBook() {
		int flightId=1;
		String seatClass="Economy";
		int noOfSeats=2;
		
		Flight f= new Flight();
		Seats seat= new Seats();
		
		seat.setAvailableSeats(5);
		seat.setSeatClass(seatClass);
		
		f.setSeat(Arrays.asList(seat));
		
		Booking book=new Booking();
		
		book.setFlightId(flightId);
		
		Passenger p = new Passenger();
		
		book.setPassengers(Arrays.asList(p));
		
		when(fc.getFlightById(flightId)).thenReturn(f);
		
		String result= sr.book(noOfSeats, seatClass, flightId, book);
		assertTrue(result.contains(result));
	}
	@Test
	void testGetAllBookings() {
		Booking b= new Booking();
		List<Booking> list= Arrays.asList(b);
		
		when(sr.getAllBookings()).thenReturn(list);
		
		List<Booking> result= br.findAll();
		assertEquals(1, result.size());
	}
	
	@Test
	void testCancelByBookingId() {
		Integer bookingId =1;
		Booking b= new Booking();
		b.setFlightId(1);
        b.setSeatClass("Economy");
        b.setNoOfSeats(2);
		
        Flight f=new Flight();
        Seats seat=new Seats();
        seat.setSeatClass("Economy");
        seat.setAvailableSeats(5);
        f.setFlightId(1);
        
        f.setSeat(Arrays.asList(seat));
        when(br.getBookingByBookingId(bookingId)).thenReturn(b);
		when(fc.getFlightById(1)).thenReturn(f);
		int result = sr.cancelByBookingId(bookingId);
		
		assertEquals(1, result);
	}
	@Test
	void testCancelByPassengerId() {
		Integer passenger_id=1;
		 Integer booking_id = 1;
		 Integer flight_id = 2;
		 String seatClass = "Economy";
		Passenger p=new Passenger();
		p.setPassenger_id(passenger_id);
		
		 Booking b = new Booking();
		 b.setBookingId(booking_id);
		 b.setFlightId(flight_id);
		 b.setSeatClass(seatClass);
		 
		 p.setBook(b);
		 
		 Flight f=new Flight();
		 f.setFlightId(flight_id);
		 
		 Seats seat = new Seats();
		 seat.setSeatClass(seatClass);
		 seat.setAvailableSeats(5);
		 f.setSeat(Arrays.asList(seat));
		    
		when(br.getDetailsByPassengerId(passenger_id)).thenReturn(p);
		when(br.getBookingByBookingId(booking_id)).thenReturn(b);
	    when(fc.getFlightById(flight_id)).thenReturn(f);
		int result=sr.cancelByPassengerId(passenger_id);
		
		assertEquals(1, result);
	}
}
