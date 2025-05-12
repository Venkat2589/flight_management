package com.flight_booking.search.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flight_booking.search.Entities.Users;
import com.flight_booking.search.jpa.userRepo;

@Service
public class userService {
	@Autowired
	private userRepo ur;
	
	@Autowired
	private JwtService sr;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public Users register(Users user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return ur.save(user);
	}
	
	public String verify(Users user) {
		Authentication auth = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(auth.isAuthenticated()) {
			return sr.generateToken(user.getUsername());
		}
		return "fail";
	}
}
