package com.jms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jms.security.SecurityUser;
import com.jms.security.SecurityUserDetailsService;
import com.jms.user.service.UserService;

@Controller
@SessionAttributes("user")
public class SecurityController {
	
	
	@GetMapping("/")
	public String home() {
		return  "home/index";
	}
	
	
	//로그인 화면
	@GetMapping("/system/loginform")
	public void loginForm() { 
	}
	
	//로그인
	@PostMapping("/system/login_proc")
	public String login(Model model, Authentication auto) {
		SecurityUser user = (SecurityUser)auto.getPrincipal();
		model.addAttribute("user", user.getUsername());
		return "home/index";
	}
	
	//로그아웃
	@GetMapping("/system/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		System.out.println("user"+ auth.getName()+"logout");
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/system/loginform";
	}
	
}
