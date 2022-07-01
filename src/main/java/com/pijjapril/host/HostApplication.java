package com.pijjapril.host;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HostApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostApplication.class, args);
	}

}
