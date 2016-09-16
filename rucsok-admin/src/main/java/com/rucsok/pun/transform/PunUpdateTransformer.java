package com.rucsok.pun.transform;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.pun.model.PunUpdateForm;
import com.rucsok.pun.service.domain.Pun;

@Component
public class PunUpdateTransformer implements Converter<Pun, PunUpdateForm> {

	@Override
	public PunUpdateForm convert(Pun source) {
		return new PunUpdateForm(source.getId(), source.getText());
	}

	public Pun convertToPun(PunUpdateForm punUpdateForm) {
		return new Pun(punUpdateForm.getId(), punUpdateForm.getText());
	}

}
