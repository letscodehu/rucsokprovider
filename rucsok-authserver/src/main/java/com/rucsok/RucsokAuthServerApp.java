package com.rucsok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rucsok" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.rucsok.test.*"))
public class RucsokAuthServerApp {

	public static void main(String[] args) {
		SpringApplication.run(RucsokAuthServerApp.class, args);
	}

}
