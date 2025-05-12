package com.flight_booking.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flight_booking.search.Entities.UserPrinciple;
import com.flight_booking.search.Entities.Users;
import com.flight_booking.search.jpa.userRepo;


@Service
public class userDetailsService implements UserDetailsService {
	
	@Autowired
	private userRepo ur;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users u = ur.findByUsername(username);
		
		if(u==null) {
			System.out.println("User not found");
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrinciple(u);
	}
	
}
