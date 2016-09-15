package com.rucsok.comment;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateService {

	public Date getCurrentTimestamp(){
		return new Date(Instant.now().toEpochMilli());
	}
	
	public LocalDateTime getLocaldateTimeFromDate(Date date){
		return new Timestamp(date.getTime()).toLocalDateTime();
	}
}
