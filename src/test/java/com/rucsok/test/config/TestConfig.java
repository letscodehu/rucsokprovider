package com.rucsok.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = {
		"com.rucsok" })
//excludeFilters = @Filter(classes= {RucsokCrawlServiceConfig.class }))
//excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "com.rucsok.test.*"))
// excludeFilters = @ComponentScan.Filter(type = FilterType.ASPECTJ, pattern =
// "com.rucsok.test.config.RucsokCrawlerServiceConfig"))
public class TestConfig {

}
