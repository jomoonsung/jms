package com.jms.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure{
	@Autowired
	private SecurityUserDetailsService userDetails;
	
	
	@Bean
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//		.authorizeHttpRequests()
//			.antMatchers("/","/system/**","/user/**","/js/**","/css/**","/images/**")
//			.permitAll()
//			.anyRequest()
//			.authenticated()
//			.and()
//				.formLogin()
//				.loginPage("/system/loginform")
//				.loginProcessingUrl("/system/login_proc")
//				.defaultSuccessUrl("/",true)
//				.usernameParameter("username")
//				.passwordParameter("userpassword");
//		
//		http
//			.logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/system/logout"))
//			.logoutSuccessUrl("/")
//			.invalidateHttpSession(true);
//		
//		return http.build();
//	}
//}
	

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {	
		security.authorizeRequests((authz) -> authz.antMatchers("/", "/system/**","/scripts/**","/user/**").permitAll());
		security.authorizeRequests((authz) -> authz.antMatchers("/board/**").authenticated());
		security.authorizeRequests((authz) -> authz.antMatchers("/admin/**").hasRole("ADMIN"));
		security.csrf().disable();
		security.formLogin().loginPage("/system/loginform").loginProcessingUrl("/system/login_proc").defaultSuccessUrl("/", true)
		.usernameParameter("username")
		.passwordParameter("userpassword");
		security.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID");
		
		return security.build();
	}
}