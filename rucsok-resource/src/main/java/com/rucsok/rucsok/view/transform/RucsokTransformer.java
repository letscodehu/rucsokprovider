package com.rucsok.rucsok.view.transform;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.SingleRucsok;
import com.rucsok.rucsok.view.model.ListedRucsokView;
import com.rucsok.rucsok.view.model.RucsokInsertRequest;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.rucsok.view.model.SingleRucsokView;
import com.rucsok.vote.domain.UserVoteType;

@Component
public class RucsokTransformer {

	public List<RucsokView> transformToView(List<Rucsok> rucsoks) {
		return rucsoks.stream().map(r -> transformToView(r)).collect(Collectors.toList());
	}
	
	public List<ListedRucsokView> transformToListedView(List<Rucsok> rucsoks) {
		return rucsoks.stream().map(r -> transformToListedView(r)).collect(Collectors.toList());
	}
	
	public ListedRucsokView transformToListedView(Rucsok rucsok) {
		ListedRucsokView result = new ListedRucsokView();
		result.setTitle(rucsok.getTitle());
		result.setId(rucsok.getId());
		result.setImageUrl(rucsok.getImageUrl());
		result.setLink(rucsok.getLink());
		result.setVideoUrl(rucsok.getVideoUrl());
		result.setType(rucsok.getType().toString().toLowerCase());
		result.setCreatedAt(rucsok.getCreatedAt().toString());
		result.setVote(rucsok.getVote());
		return result;
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
		result.setVote(rucsok.getVote());
		return result;
	}

	public SingleRucsokView transformToSingleView(SingleRucsok rucsok, UserVoteType userVoteType) {
		SingleRucsokView singleRucsokView = new SingleRucsokView();
		setCurrentRucsokView(rucsok, singleRucsokView);
		setPreviousRucsokView(rucsok, singleRucsokView);
		setNextRucsokView(rucsok, singleRucsokView);
		setCurrentVoteType(userVoteType, singleRucsokView);
		return singleRucsokView;
	}

	private void setCurrentVoteType(UserVoteType userVoteType, SingleRucsokView singleRucsokView) {
		singleRucsokView.setCurrentVoteType(userVoteType);
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
