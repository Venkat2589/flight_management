package com.flight_booking.search.Controller;
import com.flight_booking.search.service.JwtService;
import com.flight_booking.search.service.flightClient;
import com.flight_booking.search.service.serviceimpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flight_booking.flight.Entities.Flight;
import com.flight_booking.search.Entities.Users;
import com.flight_booking.search.Entities.search;
import com.flight_booking.search.Exception.FlightNotAvailable;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "http://localhost:3000")
public class controller {

	private JwtService jwtservice;
	
	private serviceimpl sr;
	
	public controller(serviceimpl sr) {
		this.sr=sr;
	}
	 
	@PostMapping("/login")
	public String login(@RequestBody Users user)
	{
		return jwtservice.generateToken(user.getUsername());
	}
	
	@GetMapping("/register")
	public String sample()
	{
		return "Validated successfully";
	}
	
//	@GetMapping("/table/{source}/{destination}/{date}")
//	public String search(@PathVariable String source, @PathVariable String destination, @PathVariable String date) {
//		
//		String string = "flight_id | destination | fare | flight_no | source | date <br>";
//		
//		for(flight f : sr.get(source, destination, date)){
//			
//			string = string + f.getFlightId()+" "+f.getDestination()+" "+f.getFare()+" "+f.getFlightNo()+" "+f.getSource()+" "+f.getDate()+" <br>";
//			
//		}
//		return string;
//	}
//	
	@GetMapping("/{source}/{destination}/{date}")
	public List<Flight>getlist(@PathVariable String source, @PathVariable String destination, @PathVariable LocalDate date)
	{
		
		List<Flight> f = sr.get(source, destination, date);
		if(f.size()==0) {
			throw new FlightNotAvailable("No flights available from " + source + " to " + destination + " on " + date);
		}
		return f;
	}
	
//	@PutMapping
//	public search update(search s) {
//		return sr.update(s);
//	}
}
