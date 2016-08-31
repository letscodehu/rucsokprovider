package com.rucsok.pun.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.pun.service.PunService;
import com.rucsok.pun.view.model.RandomPunResponse;
import com.rucsok.pun.view.transform.PunViewTransformer;

@RestController
public class RandomPunController {

	private static final String RANDOM_PUN_REQUEST_MAPPING = "/pun/random"; 
	
	@Autowired
	private PunService punService;
	
	@Autowired
	private PunViewTransformer punViewTransformer;
	
	@RequestMapping(value = RANDOM_PUN_REQUEST_MAPPING)
	public RandomPunResponse getRandomPun() {
		return punViewTransformer.transfromToRandomPunResponse(punService.serveRandom());
	}
	
}
