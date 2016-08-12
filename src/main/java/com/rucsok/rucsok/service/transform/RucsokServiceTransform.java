package com.rucsok.rucsok.service.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.repository.domain.RucsokEntity;

@Component
public class RucsokServiceTransform {
	
	@Autowired
	private RucsokTypeTransform rucsokTypeTransform;

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
		result.setType(rucsokTypeTransform.getRucsokTypeFromEntity(rucsok));
		return result;
	}

	public SingleRucsok transformToSingleRucsok(List<RucsokEntity> rucsoks) {
		SingleRucsok result = new SingleRucsok();
		if (containsOnlyOneRucsok(rucsoks)) {
			setFirstElementToCurrentRucsok(rucsoks, result);
		} else {
			setFirstToPreviousRucsok(rucsoks, result);
			setSecondToCurrentRucsok(rucsoks, result);
			setThirdToNextRucsok(rucsoks, result);
		}
		return result;
	}

	private void setFirstElementToCurrentRucsok(List<RucsokEntity> rucsoks, SingleRucsok result) {
		result.setCurrent(transformToRucsok(rucsoks.get(0)));
	}

	private boolean containsOnlyOneRucsok(List<RucsokEntity> rucsoks) {
		return 1 == rucsoks.size();
	}

	private void setSecondToCurrentRucsok(List<RucsokEntity> rucsoks, SingleRucsok result) {
		if (rucsoks.size() > 0) {
			result.setCurrent(transformToRucsok(rucsoks.get(1)));
		}
	}

	private void setThirdToNextRucsok(List<RucsokEntity> rucsoks, SingleRucsok result) {
		if (rucsoks.size() > 2) {
			result.setNext(transformToRucsok(rucsoks.get(2)));
		}
	}

	private void setFirstToPreviousRucsok(List<RucsokEntity> rucsoks, SingleRucsok result) {
		if (rucsoks.size() > 1) {
			result.setPrevious(transformToRucsok(rucsoks.get(0)));
		}
	}
}
