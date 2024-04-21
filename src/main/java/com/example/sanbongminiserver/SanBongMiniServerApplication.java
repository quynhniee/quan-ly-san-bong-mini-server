package com.example.sanbongminiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing

public class SanBongMiniServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SanBongMiniServerApplication.class, args);
	}

}
