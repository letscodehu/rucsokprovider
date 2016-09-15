package com.rucsok.pun.transform;


import org.springframework.core.convert.converter.Converter;

import com.rucsok.pun.model.PunInsertForm;
import com.rucsok.pun.service.domain.Pun;

public class PunInsertTransformer implements Converter<PunInsertForm, Pun> {

	@Override
	public Pun convert(PunInsertForm source) {
		return new Pun(0L, source.getText());
	}

}
