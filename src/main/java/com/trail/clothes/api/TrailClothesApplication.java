package com.trail.clothes.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class TrailClothesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrailClothesApplication.class, args);
	}

}
