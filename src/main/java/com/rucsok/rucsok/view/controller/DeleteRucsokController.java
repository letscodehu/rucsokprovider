package com.rucsok.rucsok.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rucsok.request.RucsokInsertRequest;
import com.rucsok.response.RucsokDeleteResponse;
import com.rucsok.rucsok.service.AlreadyExistsRucsokException;
import com.rucsok.rucsok.service.RucsokService;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.transform.RucsokTransformer;

@RestController
public class DeleteRucsokController {
	public static final String REQUEST_MAPPING = "/rucsok";

	@Autowired
	private RucsokService rucsokService;

	@RequestMapping(name = "delete-rucsok", path = REQUEST_MAPPING, method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	public void removeRucsok(@RequestParam long id) {
		rucsokService.deleteById(id);
	}
}
