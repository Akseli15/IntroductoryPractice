package com.example.zlatik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan()
public class ZlatikApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZlatikApplication.class, args);
	}
}
