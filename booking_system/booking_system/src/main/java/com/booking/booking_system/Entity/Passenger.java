package com.booking.booking_system.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Passenger {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer passenger_id;
	
	@JsonProperty("first_name")
	private String first_name;
	@JsonProperty("last_name")
	private String last_name;
	private String gender;
	private Integer seatNo;
	
	@ManyToOne
	@JoinColumn(name="booking_id",nullable = true)
	@JsonBackReference
	private Booking book;

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public Booking getBook() {
		return book;
	}
	public void setBook(Booking book) {
		this.book = book;
	}
	
	public Integer getPassenger_id() {
		return passenger_id;
	}
	public void setPassenger_id(Integer passenger_id) {
		this.passenger_id = passenger_id;
	}
	public Integer getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}
	public Passenger( String first_name, String last_name, String gender,Booking book,Integer seatNo) {

		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.book  = book;
		this.seatNo=seatNo;
	}
	public Passenger() {
	}
	
	

}
