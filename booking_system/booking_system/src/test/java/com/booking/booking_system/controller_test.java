package com.booking.booking_system;

import static org.mockito.Mockito.when;

import java.security.Provider.Service;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.controller.controller;
import com.booking.booking_system.services.serviceImpl;

public class controller_test {
	@Mock
	private serviceImpl sr;
	
	@InjectMocks
	private controller cr;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testBook() {
		Integer noOfSeats=1;
		String seatClass= "Economy";
		Integer flightId=1;
		Booking b=new Booking();
		when(sr.book(noOfSeats, seatClass, flightId, b)).thenReturn("booking done");
		String result= cr.book(noOfSeats, seatClass, flightId, b);
		assertEquals("booking done", result);
	}
	
	@Test
    void testGetBookings() {
        Booking b = new Booking();
        List<Booking> list= Arrays.asList(b);

        when(sr.getAllBookings()).thenReturn(list);

        List<Booking> result= cr.getBookings();

        assertEquals(1, result.size());
    }
	
	@Test
    void testGetBookingByUserId() {
        Integer userId = 1;
        Booking b= new Booking();

        when(sr.getBookingByUserId(userId)).thenReturn(b);

        Booking result = cr.getBookingByUserId(userId);
        assertNotNull(result);
    }
	
	 @Test
	    void testCancelByPassenger() {
	        Integer passengerId = 10;

	        when(sr.cancelByPassengerId(passengerId)).thenReturn(1);

	        int result = cr.cancelByPassenger(passengerId);

	        assertEquals(1, result);
	    }

	    @Test
	    void testCancelByBookingId() {
	        Integer bookingId = 5;

	        when(sr.cancelByBookingId(bookingId)).thenReturn(1);

	        int result = cr.cancelByBookingId(bookingId);

	        assertEquals(1, result);
	    }
}
