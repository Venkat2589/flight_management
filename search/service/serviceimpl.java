package com.flight_booking.search.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.flight_booking.flight.Entities.Flight;
import com.flight_booking.search.Exception.FlightNotAvailable;

import feign.FeignException.FeignClientException;


@Service
public class serviceimpl{
	
	private flightClient fc;
	public serviceimpl(flightClient fc) {
		this.fc = fc;
	}

	
	public List<Flight> get(String source,String destination,LocalDate date) {
		try {
		List<Flight> list= fc.getFlightsWithSearch(source, destination, date);
		}
		catch (FeignClientException e) {
			// TODO: handle exception
			throw e;
		}
		
		return fc.getFlightsWithSearch(source,destination,date);
	}


//	public List<Flight> get(String source, String destination, String date) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
