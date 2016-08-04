package com.rucsok.rucsok.service.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.domain.RucsokEntity;

@Component
public class RucsokServiceTransform {

	public List<Rucsok> transformToRucsok(List<RucsokEntity> rucsoks) {
		return rucsoks.stream().map(r -> transformToRucsok(r)).collect(Collectors.toList());
	}

	public RucsokEntity transformToRucsokEntity(Rucsok rucsok) {
		RucsokEntity result = new RucsokEntity();
		result.setImageUrl(rucsok.getImageUrl());
		result.setLink(rucsok.getLink());
		result.setTitle(rucsok.getTitle());
		result.setVideoUrl(rucsok.getVideoUrl());
		return result;
	}

	public Rucsok transformToRucsok(RucsokEntity rucsok) {
		Rucsok result = new Rucsok();
		result.setId(rucsok.getId());
		result.setTitle(rucsok.getTitle());
		result.setImageUrl(rucsok.getImageUrl());
		result.setLink(rucsok.getLink());
		result.setVideoUrl(rucsok.getVideoUrl());
		return result;
	}

	public SingleRucsok transformToSingleRucsok(List<RucsokEntity> rucsoks) {
		SingleRucsok result = new SingleRucsok();
		result.setCurrent(transformToRucsok(rucsoks.get(1)));
		result.setPrevious(transformToRucsok(rucsoks.get(0)));
		result.setNext(transformToRucsok(rucsoks.get(2)));
		return result;
	}
}
