package com.flight_booking.flight.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.flight_booking.flight.Entities.Flight;
import com.flight_booking.flight.Entities.Seats;
import com.flight_booking.flight.exception.InvalidAvailableSeats;
import com.flight_booking.flight.exception.flightNotFound;
import com.flight_booking.flight.jpa.Repository;
import com.flight_booking.flight.jpa.SeatRepository;

@Service
public class serviceimpl implements service{
	
	private Repository r;
	private SeatRepository sr;
	
	
	public serviceimpl(Repository r,SeatRepository sr) {
		this.r = r;
		this.sr = sr;
	}

	@Override
	public Flight add(Flight f) {
		List<Seats> seats = f.getSeat();
		if(seats.size() != 0) {
		for(Seats seat:seats) {
			seat.setFlight(f);
		}
		}
		return r.save(f);
	}

	@Override
	public List<Flight> getall() {
		// TODO Auto-generated method stub
		return r.findAll();
	}

	@Override
	public Flight getById(Integer id) {
		// TODO Auto-generated method stub
		return r.findById(id).orElseThrow(()->new flightNotFound("Flight with ID " + id + " not found."));
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		r.deleteById(id);
	}
	
	 public List<Flight> getAvailableFlights(String source, String destination, LocalDate date) {
	        return r.findBySourceAndDestinationAndDate(source, destination, date);
	    }
	 
	@Override
	 public Seats updateSeats(int flightId, String seatClass, int availableSeats) {
		// TODO Auto-generated method stub
		
		if(availableSeats<0) {
			throw new InvalidAvailableSeats("Available seats are negative..Please enter a positive number");
		}
			Flight f = r.findById(flightId)
	            .orElseThrow(() -> new flightNotFound("Flight with ID " + flightId + " not found."));
	        Seats seat = sr.findBySeatClassAndFlight(seatClass, f)
	                .orElseThrow(() -> new flightNotFound("Seats not found for the given flight and class"));
	        
	        seat.setAvailableSeats(availableSeats);
	        return sr.save(seat);
	    }




	
}
