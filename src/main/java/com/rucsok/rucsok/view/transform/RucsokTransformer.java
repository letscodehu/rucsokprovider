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
		return rucsoks.stream().map(r->transformToView(r)).collect(Collectors.toList());
	}
	
	public RucsokView transformToView(Rucsok rucsok) {
		RucsokView result = new RucsokView();
		result.setTitle(rucsok.getTitle());
		result.setImage(rucsok.getImage());
		result.setLink(rucsok.getLink());
		return result;
	}
	
	public SingleRucsokView transformToSingleView(SingleRucsok rucsok) {
		SingleRucsokView result = new SingleRucsokView();
		result.setCurrent(transformToView(rucsok.getCurrent()));
		result.setPreviousId(rucsok.getPrevious().getId());
		result.setNextId(rucsok.getNext().getId());
		return result;
	}

	public Rucsok transformToRucsok(RucsokInsertRequest request) {
		Rucsok result = new Rucsok();
		result.setTitle(request.getRucsok().getTitle());
		result.setImage(request.getRucsok().getImage());
		result.setLink(request.getRucsok().getLink());
		return result;
	}
}
