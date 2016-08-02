package com.rucsok.transform;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rucsok.entity.Rucsok;
import com.rucsok.response.GetRucsokResponse;

@Component
public class RucsokToGetRucsokResponse {

	public GetRucsokResponse transform(List<Rucsok> rucsoks) {
		GetRucsokResponse response = new GetRucsokResponse();
		response.rucsok = rucsoks.get(1);
		response.previous = rucsoks.get(0).getId();
		response.next = rucsoks.get(2).getId();
		return response;
		
	}
	
}
