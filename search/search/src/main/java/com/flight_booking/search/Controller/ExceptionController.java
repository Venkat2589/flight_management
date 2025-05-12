package com.flight_booking.search.Controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import feign.FeignException.FeignClientException;


@RestControllerAdvice
public class ExceptionController {
	
	
	@ExceptionHandler(FeignClientException.class)
	public ResponseEntity<Object> flightnotfound(FeignClientException e) {
		
		return ResponseEntity.status(e.status()).body(e.contentUTF8());
	}
}
