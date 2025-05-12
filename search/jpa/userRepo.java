package com.flight_booking.search.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight_booking.search.Entities.Users;

@Repository
public interface userRepo extends JpaRepository<Users, Integer> {
	
	Users findByUsername(String username);
}
