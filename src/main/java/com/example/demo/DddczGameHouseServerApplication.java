package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.demo")
@SpringBootApplication
@EnableCaching
public class DddczGameHouseServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DddczGameHouseServerApplication.class, args);
	}

}
