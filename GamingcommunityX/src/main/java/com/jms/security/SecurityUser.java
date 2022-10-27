package com.jms.security;

import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.User;

import com.jms.domain.User_1;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;
	private User_1 user;
	
	public SecurityUser(User_1 user) {
		super(user.getUsername(), user.getUserpassword(),
				AuthorityUtils.createAuthorityList(user.getRole().toString()));
		
		this.user = user;
		System.out.println(user);
	}
	
	public User_1 getMember() {
		return user;
	}
}
