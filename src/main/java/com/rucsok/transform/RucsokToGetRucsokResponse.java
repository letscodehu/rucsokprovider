package com.rucsok.transform;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rucsok.response.GetRucsokResponse;
import com.rucsok.rucsok.repository.domain.RucsokEntity;

@Component
public class RucsokToGetRucsokResponse {

	public GetRucsokResponse transform(List<RucsokEntity> rucsoks) {
		GetRucsokResponse response = new GetRucsokResponse();
		response.rucsok = rucsoks.get(1);
		response.previous = rucsoks.get(0).getId();
		response.next = rucsoks.get(2).getId();
		return response;
		
	}
	
}
