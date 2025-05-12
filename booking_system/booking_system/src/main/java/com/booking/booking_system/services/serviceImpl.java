package com.booking.booking_system.services;

import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.Flight;
import com.booking.booking_system.Entity.Passenger;
import com.booking.booking_system.Entity.Seats;
import com.booking.booking_system.Exception.bookingIdNotFound;
import com.booking.booking_system.Exception.flightNotFound;
import com.booking.booking_system.Exception.passengerNotfound;
import com.booking.booking_system.Exception.seatClassNotFound;
import com.booking.booking_system.repo.Booking_repo;
import com.booking.booking_system.repo.Repository;

import feign.FeignException.FeignClientException;

@Service
public class serviceImpl implements service{

		private Repository repo;
		private Booking_repo br;
		FlightClient fc;
		
		public serviceImpl (Repository repo,FlightClient fc,Booking_repo br) {
			this.repo=repo;
			this.fc=fc;
			this.br= br;
		}
	@Override
	public Passenger getPassenger(Integer passenger_id) {
		Passenger p= repo.findById(passenger_id).orElseThrow(()->new passengerNotfound("passenger not found with passengerId "+passenger_id));
		return p;
	}

	@Override
	public String book( Integer noOfSeats, String seatClass,Integer flightId, Booking book) {
		// TODO Auto-generated method stub
		try {
		Flight f = fc.getFlightById(flightId);
//		 if (f==null) {
//	            throw new flightNotFound("Flight not found on flightId "+flightId);
//	        }
		 int flag =0;
		 for(Seats s: f.getSeat()) {
			 if(s.getSeatClass().equalsIgnoreCase(seatClass)) {
				 flag=1;
			 }
		 }
		 
		 if(flag==0) {
			 throw new seatClassNotFound("The Provided seatclass is not present in flight-id "+flightId);
		 }
	
        Optional<Integer> availableSeats = f.getSeat().stream()
                .filter(seat -> seat.getSeatClass().equalsIgnoreCase(seatClass))
                .map(seat -> seat.getAvailableSeats())
                .findFirst();
        
        if(availableSeats.isEmpty() || availableSeats.get()<noOfSeats) {
        	throw new seatClassNotFound("Seats are not available for this particular class");
        }
        
        int updatedSeats = availableSeats.get() - noOfSeats;
        fc.updateSeats(flightId, seatClass, updatedSeats);
        
        book.setBookingTime(LocalDateTime.now());
        book.setSeatClass(seatClass);
        
       List<Passenger> list= book.getPassengers();
       Integer seats = availableSeats.get();
        for(Passenger p: list) {
        	p.setSeatNo(seats);
        	seats--;
        	p.setBook(book);
        }
        
        for (Passenger passenger : book.getPassengers()) {
            passenger.setBook(book);
        }
        
		br.save(book);
		String sb = "Congratulations!! Your Booking is completed."; 
		
		for(Passenger p:book.getPassengers()) {
			sb = sb+" Passenger-Name is "+p.getFirst_name()+" and id= "+p.getPassenger_id();
		}
		
		sb = sb+". Please proceed for check-in";
		
		return sb;
		}
		catch (FeignClientException e) {
			throw e;
		}
		 
	}
	
	@Override
	public List<Booking> getAllBookings() {
	        return br.findAll();
	    }
	@Override
	public Booking getBookingByUserId(Integer userId) {
	        return br.getBookingByUserId(userId);
	    }
	@Override
	public int cancelByBookingId(Integer bookingId) {
		// TODO Auto-generated method stub
		Booking b = br.getBookingByBookingId(bookingId);
		if(b== null) {
			throw new bookingIdNotFound("booking Id is not available..Please Enter Correct booking Id");
		}
		br.deleteById(bookingId);
		Flight f = fc.getFlightById(b.getFlightId());
		
		
		 Optional<Integer> availableSeats = f.getSeat().stream()
	                .filter(seat -> seat.getSeatClass().equalsIgnoreCase(b.getSeatClass()))
	                .map(seat -> seat.getAvailableSeats())
	                .findFirst();
		 fc.updateSeats(b.getFlightId(), b.getSeatClass(), availableSeats.get()+b.getNoOfSeats());
		System.out.println("Successfully Canceled..Please use our service again");
		return 1;
	}
	@Override
	public int cancelByPassengerId(Integer passengerId) {
		// TODO Auto-generated method stub
		Passenger p = br.getDetailsByPassengerId(passengerId);
		if(p==null) {
			throw new passengerNotfound("Passenger Not Found for the given passengerId "+passengerId);
		}
		br.cancelBookingByPassengerId(passengerId);
		Booking b = br.getBookingByBookingId(p.getBook().getBookingId());
		
		Flight f = fc.getFlightById(b.getFlightId());
		
		
		 Optional<Integer> availableSeats = f.getSeat().stream()
	                .filter(seat -> seat.getSeatClass().equalsIgnoreCase(b.getSeatClass()))
	                .map(seat -> seat.getAvailableSeats())
	                .findFirst();
		 fc.updateSeats(b.getFlightId(), b.getSeatClass(), availableSeats.get()+1);
		
		System.out.println("Successfully Canceled..Please use our service again");
		return 1;
	}

}
