package com.metrodata.edutrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EdutrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(EdutrackApplication.class, args);
		System.out.println("SUCCESS! Check MySQL");
	}

}
