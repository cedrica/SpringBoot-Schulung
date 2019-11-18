package com.springboot.schulung;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.springboot.schulung")
@SpringBootApplication
public class SchulungApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchulungApplication.class, args);
	}

}
