package com.rucsok.rucsok.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.repository.dao.ListedRucsokRepository;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.rucsok.transform.DateTransformer;
import com.rucsok.rucsok.transform.ListedRucsokTransformer;

@Service
public class ListedRucsokService {

	@Value("${rucsok.hot.page.size}")
	public static int HOT_PAGINATION_SIZE = 3;

	@Autowired
	private ListedRucsokRepository listedRucsokRepository;

	@Autowired
	private ListedRucsokTransformer listedRucsokTransformer;
	
	@Autowired
	private DateTransformer dateTransformer;

	public List<Rucsok> getListedRucsok(LocalDate date) {
		if(null == date){
			throw new IllegalRucsokArgumentException();
		}
		return listedRucsokTransformer.transformToRucsokList(
				listedRucsokRepository.getHotRucsok(
						dateTransformer.getCurrentDay(date), 
						dateTransformer.getNextDay(date), 
						HOT_PAGINATION_SIZE
						)
				);
	}
}
