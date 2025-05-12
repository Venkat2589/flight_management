package com.flight_booking.search.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.flight_booking.flight.Entities.Flight;

@FeignClient(name="FLIGHT")
public interface flightClient {
	
	@GetMapping("/flight/{source}/{destination}/{date}")
	List<Flight> getFlightsWithSearch(@PathVariable String source,@PathVariable  String destination,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

}
