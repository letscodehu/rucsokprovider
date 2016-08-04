package com.rucsok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = "classpath:context.xml")
@ComponentScan(basePackages = { "com.rucsok" },
excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.rucsok.test.*"))
public class AcademyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcademyApplication.class, args);
	}
}
