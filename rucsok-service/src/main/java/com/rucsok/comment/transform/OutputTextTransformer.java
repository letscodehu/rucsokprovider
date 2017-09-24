package com.rucsok.comment.transform;

import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

@Component
public class OutputTextTransformer {

	private static final String ENCODING = "UTF-8";

	public String escape(String input) {
		return HtmlUtils.htmlEscape(input, ENCODING);
	}

}
