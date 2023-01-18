package com.example.demo.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PassGen {
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		System.out.println(encoder.encode("darsha"));
		System.out.println(encoder.encode("yash"));
		System.out.println(encoder.encode("palak"));
	}

}

//$2a$10$kfapPVc/ls/cbrpQE4lR7u1cNW5yzn/6TbESwdBtJC6tVIVEUMEC2
//$2a$10$IllOgtoqpKHff4x1DAzB8.mE/IuHbVR2yoSphJAw1AplZOnwrG9H6
//$2a$10$xpqoOEbPoBP49PurWjf.R.4MQUyImVhFdBCKaL9QDo8h2GO/YwxEO