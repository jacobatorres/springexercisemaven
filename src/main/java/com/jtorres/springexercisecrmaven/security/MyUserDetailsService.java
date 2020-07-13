package com.jtorres.springexercisecrmaven.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Value("${username_valid}")
	private String username_config;

	@Value("${password_valid}")
	private String password_config;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// username is the variable provided by the app
		return new User(username_config, password_config, new ArrayList<>());
	}

}
