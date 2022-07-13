package com.luna.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.luna"})
public class LunaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LunaApplication.class, args);
	}

}
