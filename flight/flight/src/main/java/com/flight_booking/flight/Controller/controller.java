package com.flight_booking.flight.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_booking.flight.Entities.Flight;
import com.flight_booking.flight.Entities.Seats;
import com.flight_booking.flight.exception.flightNotFound;
import com.flight_booking.flight.services.service;

@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "http://localhost:3000")
public class controller {
	
	private final service sr;
	
	public controller(service sr) {
		this.sr= sr;
	}
	
	@PostMapping
	public Flight create(@RequestBody Flight f) {
		return sr.add(f);
	}
	
	@GetMapping("/list")
	public List<Flight> get(){
		return sr.getall();
	
	}

	@GetMapping("/{flightId}")
	public Flight getById(@PathVariable Integer flightId)  {
		return sr.getById(flightId);
	}
	
	@DeleteMapping("/{flightId}")
	public void delete(@PathVariable Integer flightId) {
		Flight f=sr.getById(flightId);

		sr.deleteById(flightId);
	}
	
	
	@GetMapping("/{source}/{destination}/{date}")
	public List<Flight> getAvailableFlights(@PathVariable String source,@PathVariable String destination,@PathVariable LocalDate date) throws flightNotFound{
		if(sr.getAvailableFlights(source, destination, date).size() == 0) throw new flightNotFound("Flights are not available on this route...");
		return sr.getAvailableFlights(source, destination, date);
	}
	
	
	@PutMapping("/seats")
    public Seats updateSeats(@RequestParam Integer flightId,@RequestParam String seatClass,@RequestParam Integer availableSeats) {
        return sr.updateSeats(flightId, seatClass, availableSeats);
    }
	
	
}

