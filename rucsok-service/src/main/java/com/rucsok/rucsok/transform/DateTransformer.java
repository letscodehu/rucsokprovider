package com.rucsok.rucsok.transform;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DateTransformer {

	private DateTimeFormatter formatter;

	@Autowired
	public DateTransformer(DateTimeFormatter rucsokDateFormatter) {
		this.formatter = rucsokDateFormatter;
	}

	public String getCurrentDay(LocalDate date) {
		return date.format(formatter);
	}

	public String getNextDay(LocalDate date) {
		return date.plusDays(1).format(formatter);
	}
}
