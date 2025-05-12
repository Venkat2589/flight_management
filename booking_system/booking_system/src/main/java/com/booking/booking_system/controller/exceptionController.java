package com.booking.booking_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.BookingException;
import com.booking.booking_system.Exception.bookingIdNotFound;
import com.booking.booking_system.Exception.flightNotFound;
import com.booking.booking_system.Exception.passengerNotfound;
import com.booking.booking_system.Exception.seatClassNotFound;

import ch.qos.logback.core.status.ErrorStatus;
import feign.FeignException.FeignClientException;

@RestControllerAdvice
public class exceptionController {
	
	@ExceptionHandler(passengerNotfound.class)
	public ResponseEntity<Object> passengerNotFound(Exception e) {
		
		BookingException be = new BookingException(1,e.getMessage(),"Enter a Valid passenger Id.");
		return new ResponseEntity(be, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@ExceptionHandler(seatClassNotFound.class)
	public ResponseEntity<Object> seat(Exception e) {
		BookingException be= new BookingException(1,e.getMessage(),"Enter correct seat class");
		return new ResponseEntity(be,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<Object> booking(FeignClientException e) {
		return ResponseEntity
                .status(e.status())
                .body(e.contentUTF8());
	}
	
	@ExceptionHandler(bookingIdNotFound.class)
	public ResponseEntity<Object> book(bookingIdNotFound e){
		BookingException be= new BookingException(1,e.getMessage(),"Enter Correct BookingId");
		return new ResponseEntity(be,HttpStatus.BAD_REQUEST);
	}
}
