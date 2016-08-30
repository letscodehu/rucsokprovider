package com.rucsok.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {
		"com.rucsok" })
@PropertySource("classpath:/application.properties")
public class TestConfig {

}
