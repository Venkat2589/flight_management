package com.booking.booking_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.booking.booking_system.Entity.Booking;
import com.booking.booking_system.Entity.Passenger;

import feign.Param;
import jakarta.transaction.Transactional;

public interface Booking_repo extends JpaRepository<Booking, Integer>{
	@Query(value = "SELECT * FROM booking WHERE booking_id = ?1", nativeQuery = true)
	public Booking getBookingByBookingId(Integer bookingId);
	
	@Query(value = "SELECT * FROM booking WHERE user_id = ?1", nativeQuery = true)
	public Booking getBookingByUserId(Integer userId);
	
	@Query(value = "SELECT * FROM passenger WHERE passenger_id = ?1", nativeQuery = true)
	public Passenger getDetailsByPassengerId(Integer passengerId);
	

	
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM Passenger WHERE passenger_id = ?1",nativeQuery = true)
    int cancelBookingByPassengerId( Integer passengerId);
	
}
