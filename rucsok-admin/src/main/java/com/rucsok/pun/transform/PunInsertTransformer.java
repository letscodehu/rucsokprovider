package com.rucsok.pun.transform;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.pun.model.PunInsertForm;
import com.rucsok.pun.service.domain.Pun;

@Component
public class PunInsertTransformer implements Converter<PunInsertForm, Pun> {

	@Override
	public Pun convert(PunInsertForm source) {
		return new Pun(0L, source.getText());
	}

}
