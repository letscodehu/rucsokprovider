package com.rucsok.pun.service.transform;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rucsok.pun.service.domain.Pun;
import com.rucsok.pun.repository.domain.PunEntity;


@Component
public class PunServiceTransformer implements Converter<PunEntity, Pun> {

	public Pun convert(PunEntity pun) {
		return new Pun(pun.getId(), pun.getText());
	}

	public PunEntity transformDTOToEntity(Pun pun) {
		PunEntity punEntity = new PunEntity();
		punEntity.setText(pun.getText());
		return punEntity;
	}

}
