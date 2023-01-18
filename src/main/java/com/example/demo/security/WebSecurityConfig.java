package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity

public class WebSecurityConfig {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;

	}

	
	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests()
	    .requestMatchers("/").hasAnyAuthority("USER","EDITOR", "ADMIN")
	    .requestMatchers("addemployee").hasAnyAuthority("EDITOR","ADMIN")
	    .requestMatchers("/edit/{id}**").hasAnyAuthority( "EDITOR", "ADMIN")
	    .requestMatchers("/delete/{id}**").hasAnyAuthority("ADMIN")
	    .requestMatchers("mysql-console/**").permitAll()
	    .anyRequest().authenticated()
	    .and()
	    .formLogin()
	    .permitAll()
	    .and()
	    .exceptionHandling()
	    .accessDeniedPage("/403") ;
	  
	  http.csrf().disable();
	  http.headers().frameOptions().disable();
	  http.authenticationProvider(authenticationProvider());
	  return http.build();
	 }
	 


}
