package com.flight_booking.search;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.flight_booking.flight.Entities.Flight;
import com.flight_booking.flight.jpa.Repository;
import com.flight_booking.flight.services.serviceimpl;

public class service_test {
	
	@Mock
    private Repository r;

    @InjectMocks
    private serviceimpl sr; 

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAvailableFlights() {

        String source= "NYC";
        String destination= "SYO";
        LocalDate date= LocalDate.now();

        Flight f= new Flight();
        f.setSource(source);
        f.setDestination(destination);
        f.setDate(date);
        List<Flight>list= Arrays.asList(f);

        when(r.findBySourceAndDestinationAndDate(source, destination, date)).thenReturn(list);

        List<Flight>result= sr.getAvailableFlights(source, destination, date);

        assertEquals(1, result.size());
		
	}
}
