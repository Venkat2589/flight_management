package com.flight_booking.flight.Entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatClass; 
    private int noOfSeats;
    private int availableSeats;
    
    @ManyToOne
    @JoinColumn(name = "flight_Id",nullable = true)
    @JsonBackReference
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
