package com.rucsok.pun.transform;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.domain.Pun;

@Component
public class PunUpdateTransformer implements Converter<Pun, PunUpdateForm> {

	@Override
	public PunUpdateForm convert(Pun source) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pun convertToPun(PunUpdateForm mockPun) {
		// TODO Auto-generated method stub
		return null;
	}

}
