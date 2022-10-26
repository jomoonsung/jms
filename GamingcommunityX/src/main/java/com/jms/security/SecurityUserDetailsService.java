package com.jms.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jms.domain.User_1;
import com.jms.persistence.UserRepository;

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
	
	
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User_1> optional = userRepo.findByUsername(username);
		if (!optional.isPresent()) {
			throw new UsernameNotFoundException(username + "사용자 없음"); 
		}else {
			User_1 user  = optional.get();
			
			return new SecurityUser(user);
		}
	}
	
}
