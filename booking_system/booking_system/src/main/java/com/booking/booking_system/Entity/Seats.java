package com.booking.booking_system.Entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

public class Seats {

    private int id;
    private String seatClass; 
    private int noOfSeats;
    private int availableSeats;

    private Flight flight;

    public int getId() { 
    	return id; 
    	}
    public String getSeatClass() { 
    	return seatClass; 
    	}
    public void setSeatClass(String seatClass) { 
    	this.seatClass = seatClass; 
    	}
    public int getNoOfSeats() { 
    	return noOfSeats; 
    	}
    public void setNoOfSeats(int noOfSeats) { 
    	this.noOfSeats = noOfSeats; 
    	}
    public int getAvailableSeats() { 
    	return availableSeats; 
    	}
    public void setAvailableSeats(int availableSeats) { 
    	this.availableSeats = availableSeats; 
    	}
    public Flight getFlight() { 
    	return flight; 
    	}
    public void setFlight( Flight flight) { 
    	this.flight = flight; 
    	}
    

    public Seats() {
    	
    }

    public Seats(String seatClass, int noOfSeats, int availableSeats,Flight flight) {
        this.seatClass = seatClass;
        this.noOfSeats = noOfSeats;
        this.availableSeats = availableSeats;
        this.flight=flight;
    }
}
