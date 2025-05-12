package com.flight_booking.search.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

public class flight {
	
	private  int flightId;
	private String flightNo;
	private String source;
	private String destination;
	private String date;
	private int fare;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
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
	
	public flight(int flightId, String flightNo, String source, String destination, String date,int fare) {
		
		this.flightId = flightId;
		this.flightNo = flightNo;
		this.source = source;
		this.destination = destination;
		this.date=date;
		this.fare = fare;
	}
	public flight() {
		
	}
	
	
	
}
