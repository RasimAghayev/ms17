package com.example.ms17;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Ms17Application {

	@Value("${has.account}")
	String hasAccount;
	public static void main(String[] args) {
		SpringApplication.run(Ms17Application.class, args);
		log.info("Application is starting!");

		log.warn("Sdfdf");
	}

}
