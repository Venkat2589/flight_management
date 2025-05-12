package com.flight_booking.flight.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Flight {
	@Id
	private int flightId;
	private String flightNo;
	private String source;
	private String destination;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yy", locale = "en")
	@Column(name = "date")
	private LocalDate date;
	private int fare;
	
	
	@OneToMany(mappedBy = "flight",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Seats> seat;	
	
	public List<Seats> getSeat() {
		return seat;
	}
	public void setSeat(List<Seats> seat) {
		this.seat = seat;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	
	
	public Flight(int flightId, String flightNo, String source, String destination, LocalDate date,int fare,List<Seats>seat ) {
		
		this.flightId = flightId;
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.date=date;
		this.fare = fare;
		this.seat = seat; 
	}
	public Flight() {
		
	}
	
	
	
}
