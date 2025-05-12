package com.checkin.Checkin.service;

import java.text.BreakIterator;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.checkin.Checkin.Entity.CheckIn;
import com.checkin.Checkin.Entity.Passenger;
import com.checkin.Checkin.Repo.CheckIn_repo;

import feign.FeignException.FeignClientException;

@Service
public class serviceImpl implements service{
	
	private CheckIn_repo cr;
	Booking_client bc;
	
	public serviceImpl(CheckIn_repo cr,Booking_client bc) {
		this.bc=bc;
		this.cr=cr;
	}
	
	@Override
	public String checkIn(Integer passenger_id) {
		try {
		Passenger p = bc.getPassenger(passenger_id); 
		}
		catch (FeignClientException e) {
			// TODO: handle exception
			throw e;
		}
		Passenger p = bc.getPassenger(passenger_id);
		CheckIn c = new CheckIn(); 
		Optional<CheckIn> s= cr.findById(passenger_id);
		
		if(s.isPresent()) {
			return "Already CheckedIn.Please wait in the Lobby.";
		}
		else {
			c.setPassenger_id(passenger_id);
			c.setStatus(1);
			
			cr.save(c);
			
		}
		return "CheckedIn.. Mr/Miss "+p.getFirst_name()+" "+p.getLast_name()+" Your seat no is "+p.getSeatNo()+".Have a safe trip.";
	}

}
