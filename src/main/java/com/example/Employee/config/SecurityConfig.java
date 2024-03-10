package com.example.Employee.config;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig 
{
	
	@Autowired
	private JwtAuthConverter jwtAuthConverter;
	
	
	@Bean
	 SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
			
		http.csrf()
			.disable()
			.authorizeHttpRequests()
			.anyRequest()
			.authenticated();
		http.oauth2ResourceServer()
			.jwt().jwtAuthenticationConverter(jwtAuthConverter);
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
		return http.build();
		
	}
	
}