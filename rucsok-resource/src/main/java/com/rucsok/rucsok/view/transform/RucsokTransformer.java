package com.rucsok.rucsok.view.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
		result.setId(rucsok.getId());
		result.setImageUrl(rucsok.getImageUrl());
		result.setLink(rucsok.getLink());
		result.setVideoUrl(rucsok.getVideoUrl());
		result.setType(rucsok.getType().toString().toLowerCase());
		result.setUsername(rucsok.getUser().getUsername());
		result.setCreatedAt(rucsok.getCreatedAt());
		return result;
	}

	public SingleRucsokView transformToSingleView(SingleRucsok rucsok) {
		SingleRucsokView result = new SingleRucsokView();
		setCurrentRucsokView(rucsok, result);
		setPreviousRucsokView(rucsok, result);
		setNextRucsokView(rucsok, result);
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

	private void setNextRucsokView(SingleRucsok rucsok, SingleRucsokView result) {
		if (null != rucsok.getNext()) {
			result.setNextId(rucsok.getNext().getId());
		}
	}

	private void setPreviousRucsokView(SingleRucsok rucsok, SingleRucsokView result) {
		if (null != rucsok.getPrevious()) {
			result.setPreviousId(rucsok.getPrevious().getId());
		}
	}

	private void setCurrentRucsokView(SingleRucsok rucsok, SingleRucsokView result) {
		if (null != rucsok.getCurrent()) {
			result.setCurrent(transformToView(rucsok.getCurrent()));
		}
	}

}
