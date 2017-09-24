package com.rucsok.rucsok.transform;

import java.math.BigInteger;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;

@Component
public class ListedRucsokTransformer {
	
	@Autowired
	private RucsokTypeTransform rucsokTypeTransform;

	public static final String DATE_FORMAT = "yyyy-MM-dd hh:mm:ss.SSS";

	public List<Rucsok> transformToRucsokList(List<Object[]> resultSet) {
		return (List<Rucsok>) resultSet.stream().map(o -> transformToRucsok(o)).collect(Collectors.toList());
	}

	public Rucsok transformToRucsok(Object[] object) {
		Rucsok result = new Rucsok();
		result.setUpVote(intFromBigIntObject(object[0]));
		result.setDownVote(intFromBigIntObject(object[1]));
		result.setId(longFromBigIntObject(object[2]));
		result.setTitle(String.valueOf(object[3]));
		result.setLink(String.valueOf(object[4]));
		result.setImageUrl(String.valueOf(object[5]));
		result.setVideoUrl(String.valueOf(object[6]));
		result.setCreatedAt(timeStampFromString(String.valueOf(object[7])));
		result.setType(rucsokTypeTransform.getRucsokTypeFromString(String.valueOf(object[6])));
		return result;
	}

	private LocalDateTime timeStampFromString(String timestamp) {
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			Date parsedDate;
			parsedDate = (Date) dateFormat.parse(timestamp);
			return new java.sql.Timestamp(parsedDate.getTime()).toLocalDateTime();
		} catch (ParseException e) {
			throw new IllegalArgumentException("Date cannot parsed: " + e.toString());
		}
	}

	private int intFromBigIntObject(Object object) {
		return ((BigInteger) object).intValue();
	}

	private long longFromBigIntObject(Object object) {
		return ((BigInteger) object).longValue();
	}
}
