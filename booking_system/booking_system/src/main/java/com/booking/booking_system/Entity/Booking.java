package com.booking.booking_system.Entity;

import java.time.LocalDateTime;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Booking {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private Integer userId;
	private Integer flightId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "booking_time", columnDefinition = "DATETIME")
	private LocalDateTime BookingTime;
	private Integer noOfSeats;
	private String seatClass;
	
	@OneToMany(mappedBy = "book",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Passenger> passengers;
	
	
	
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public LocalDateTime getBookingTime() {
		return BookingTime;
	}
	public void setBookingTime(LocalDateTime bookingTime) {
		BookingTime = bookingTime;
	}
	public Integer getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(Integer noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	
	public String getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	public Booking(Integer bookingId,Integer userId, Integer flightId, LocalDateTime bookingTime, Integer noOfSeats,String seatClass,List<Passenger>passengers) {
		this.bookingId =bookingId;
		this.userId = userId;
		this.flightId = flightId;
		BookingTime = bookingTime;
		this.noOfSeats = noOfSeats;
		this.seatClass=seatClass;
		this.passengers = passengers;
	}
	public Booking() {
	
	}
	
	
}
