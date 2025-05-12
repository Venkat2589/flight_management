package com.flight_booking.search.secutityConfig;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class config {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTFilter JwtFilter;
	
	 @Bean
	public SecurityFilterChain sfc(HttpSecurity http) throws Exception {
		 http.csrf(Customizer->Customizer.disable());
		 http.authorizeHttpRequests(request->request
				 .requestMatchers("register","login")
				 .permitAll()
				 .anyRequest().authenticated());
		// http.formLogin(Customizer.withDefaults()); 
		 http.httpBasic(Customizer.withDefaults());
		 http.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 http.addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
	 
	 @Bean
	 public AuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		 provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
		 provider.setUserDetailsService(userDetailsService);
		 return provider;
	 }
	 
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		 return config.getAuthenticationManager();
	 }
}
