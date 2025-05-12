package com.checkin.Checkin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.checkin.Checkin.Entity.CheckIn;
import com.checkin.Checkin.Entity.Passenger;
import com.checkin.Checkin.Repo.CheckIn_repo;
import com.checkin.Checkin.service.serviceImpl;
import com.checkin.Checkin.service.Booking_client;

class service_test {

    @Mock
    private CheckIn_repo checkInRepo;

    @Mock
    private Booking_client bookingClient;

    @InjectMocks
    private serviceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIn_SuccessfulCheckIn() {
        int passengerId = 1;
        Passenger passenger = new Passenger();
        passenger.setFirst_name("John");
        passenger.setLast_name("Doe");
        passenger.setSeatNo(50);

        when(bookingClient.getPassenger(passengerId)).thenReturn(passenger);
        when(checkInRepo.findById(passengerId)).thenReturn(Optional.empty());

        String result = service.checkIn(passengerId);

        assertEquals("CheckedIn.. Mr/Miss John Doe Your seat no is 50.Have a safe trip.", result);
      
    }

    @Test
    void testCheckIn_AlreadyCheckedIn() {
        int passengerId = 1;
        CheckIn checkIn = new CheckIn();
        checkIn.setPassenger_id(passengerId);
        checkIn.setStatus(1);

        when(checkInRepo.findById(passengerId)).thenReturn(Optional.of(checkIn));

        String result = service.checkIn(passengerId);

        assertEquals("Already CheckedIn.Please wait in the Lobby.", result);
  
    }
}
