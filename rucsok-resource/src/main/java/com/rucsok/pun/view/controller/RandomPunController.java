package com.rucsok.pun.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.pun.service.RandomPunService;
import com.rucsok.pun.view.model.RandomPunResponse;
import com.rucsok.pun.view.transform.PunViewTransformer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Pun"}, description = "The Endpoint serves a random pun from the database in order to pun-ish the ones who aren't rucsking for several minutes")
@RestController
public class RandomPunController {

	public static final String RANDOM_PUN_REQUEST_MAPPING = "/pun/random"; 
	
	@Autowired
	private RandomPunService punService;
	
	@Autowired
	private PunViewTransformer punViewTransformer;
	
	@ApiOperation(produces = "application/json", nickname= "Random pun", value = "")
	@RequestMapping(value = RANDOM_PUN_REQUEST_MAPPING, method = RequestMethod.GET)
	public RandomPunResponse getRandomPun() {
		return punViewTransformer.transfromToRandomPunResponse(punService.serveRandom());
	}
	
}
