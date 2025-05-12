package com.checkin.Checkin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.checkin.Checkin.Controller.checkIn_controller;
import com.checkin.Checkin.service.serviceImpl;

class controller_test {

    @Mock
    private serviceImpl sr; 

    @InjectMocks
    private checkIn_controller cr;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckIn_Success() {
        int passengerId = 1;
        String s = "CheckedIn.. Mr/Miss vivek kumar Your seat no is 50. Have a safe trip.";

        when(sr.checkIn(passengerId)).thenReturn(s);

        String response = cr.checkIn(passengerId); 

        assertEquals(s, response);
    }

    @Test
    void testCheckIn_AlreadyCheckedIn() {
        int passengerId = 2;
        String s = "Already CheckedIn.Please wait in the Lobby.";

        when(sr.checkIn(passengerId)).thenReturn(s);

        String response = cr.checkIn(passengerId);

        assertEquals(s, response);
    }
}
