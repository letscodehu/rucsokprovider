package com.rucsok.rucsok.transform.config;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformConfig {

	private static final String DATE_FORMAT = "yyyy-MM-dd";

	@Bean
	public DateTimeFormatter rucsokDateFormatter(){
		return DateTimeFormatter.ofPattern(DATE_FORMAT);
	}
}
