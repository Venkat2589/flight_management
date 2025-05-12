package com.booking.booking_system.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.booking.booking_system.Entity.Passenger;

public interface Repository extends JpaRepository<Passenger, Integer> {
	
}
