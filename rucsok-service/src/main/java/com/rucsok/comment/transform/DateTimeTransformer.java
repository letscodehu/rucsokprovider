package com.rucsok.comment.transform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class DateTimeTransformer {
	
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy.MM.dd HH:mm:ss";
	
	private DateTimeFormatter formatter;
	
	public DateTimeTransformer() {
		formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
	}

	public String format(LocalDateTime timestamp){
		return timestamp.format(formatter);
	}
	
}
