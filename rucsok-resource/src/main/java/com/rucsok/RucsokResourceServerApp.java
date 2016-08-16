package com.rucsok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.rucsok" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.rucsok.test.*"))
public class RucsokResourceServerApp {

	public static void main(String[] args) {
		SpringApplication.run(RucsokResourceServerApp.class, args);
	}

}
