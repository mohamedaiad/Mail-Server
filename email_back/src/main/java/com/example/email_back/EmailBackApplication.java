package com.example.email_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class EmailBackApplication {

	public static void main(String[] args) {

//		SpringApplication.run(PaintBackApplication.class, args);
		var ctx = new SpringApplicationBuilder(EmailBackApplication.class)
				.headless(false).run(args);

	}

}