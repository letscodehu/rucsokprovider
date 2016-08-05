package com.rucsok.rucsok.view.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.view.model.RucsokInsertRequest;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.model.SingleRucsokView;

@Component
public class RucsokTransformer {

	public List<RucsokView> transformToView(List<Rucsok> rucsoks) {
		return rucsoks.stream().map(r -> transformToView(r)).collect(Collectors.toList());
	}

	public RucsokView transformToView(Rucsok rucsok) {
		RucsokView result = new RucsokView();
		result.setTitle(rucsok.getTitle());
		result.setImageUrl(rucsok.getImageUrl());
		result.setLink(rucsok.getLink());
		result.setVideoUrl(rucsok.getVideoUrl());
		return result;
	}

	public SingleRucsokView transformToSingleView(SingleRucsok rucsok) {
		SingleRucsokView result = new SingleRucsokView();
		if (null != rucsok.getCurrent()) {
			result.setCurrent(transformToView(rucsok.getCurrent()));
		}
		if (null != rucsok.getPrevious()) {
			result.setPreviousId(rucsok.getPrevious().getId());
		}
		if (null != rucsok.getNext()) {
			result.setNextId(rucsok.getNext().getId());
		}
		return result;
	}

	public Rucsok transformToRucsok(RucsokInsertRequest request) {
		Rucsok result = new Rucsok();
		result.setTitle(request.getRucsok().getTitle());
		result.setImageUrl(request.getRucsok().getImageUrl());
		result.setLink(request.getRucsok().getLink());
		result.setVideoUrl(request.getRucsok().getVideoUrl());
		return result;
	}
}
