package com.example.servertuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class ServertutoApplication {

	public static void main(String[] args) {


		SpringApplication.run(ServertutoApplication.class, args);
	}

}
