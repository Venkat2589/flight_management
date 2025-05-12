package com.checkin.Checkin.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CheckIn {
	@Id
	private Integer passenger_id;
	
	private Integer status;

	public Integer getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(Integer passenger_id) {
		this.passenger_id = passenger_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public CheckIn(Integer passenger_id, Integer status) {
	
		this.passenger_id = passenger_id;
		this.status = status;
	}

	public CheckIn() {
		
	}
	
	
	
	
}
