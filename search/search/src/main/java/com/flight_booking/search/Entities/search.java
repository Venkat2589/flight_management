package com.flight_booking.search.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public class search extends flight {
	private String source;
	private String destination;
	private String date;
	
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public search(int flightId, String flightNo, String source, String destination, String date, int fare) {
		super();
		this.source = source;
		this.destination = destination;
		this.date = date;
	}
	public search() {
	}
	
	
	
}
