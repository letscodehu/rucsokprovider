package com.rucsok.pun.view.transform;

import org.springframework.stereotype.Component;

import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.view.model.RandomPunResponse;

@Component
public class PunViewTransformer {

	public RandomPunResponse transfromToRandomPunResponse(Pun punDTO) {
		return new RandomPunResponse(punDTO);
		
	}

}
