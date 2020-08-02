package com.college.placementsystem.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.college.placementsystem.entities.Authority;
import com.college.placementsystem.entities.User;
import com.college.placementsystem.model.LoginRequest;
import com.college.placementsystem.repositories.AuthorityRepository;
import com.college.placementsystem.repositories.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}
	
	public String loadPermissionsByUsername(String username) throws UsernameNotFoundException {
		Authority authority = authorityRepository.findByUsername(username);
		if (authority == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return authority.getAuthority();
	}
	
	public User save(LoginRequest loginRequest) {
		User newUser = new User();
		newUser.setUsername(loginRequest.getUsername());
		newUser.setPassword(bcryptEncoder.encode(loginRequest.getPassword()));
		userRepository.save(newUser);
		Authority newAuthority = new Authority();
		newAuthority.setUsername(loginRequest.getUsername());
		newAuthority.setAuthority("ROLE_USER");
		authorityRepository.save(newAuthority);
		return newUser;
	}
}
