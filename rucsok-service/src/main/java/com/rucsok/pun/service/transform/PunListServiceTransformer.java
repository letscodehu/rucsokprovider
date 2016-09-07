package com.rucsok.pun.service.transform;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.pun.repository.domain.PunEntity;
import com.rucsok.pun.service.domain.Pun;

@Component
public class PunListServiceTransformer {

	private PunServiceTransformer transformer;
		
	@Autowired
	public PunListServiceTransformer(PunServiceTransformer transformer) {
		super();
		this.transformer = transformer;
	}
	
	public List<Pun> transfromFromEntityList(List<PunEntity> entities) {
		List<Pun> puns = new ArrayList<Pun>();
		for (PunEntity entity : entities) {
			puns.add(transformer.transformEntityToDTO(entity));
		}
		return puns;
	}

}
