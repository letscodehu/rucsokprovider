package com.rucsok.rucsok.transform;

import org.springframework.stereotype.Component;

import com.rucsok.rucsok.domain.RucsokType;
import com.rucsok.rucsok.repository.domain.RucsokEntity;

@Component
public class RucsokTypeTransform {

	private static final String WEBM = "webm";
	private static final String OGG = "ogg";
	private static final String OGV = "ogv";
	private static final String MP4 = "mp4";

	public RucsokType getRucsokTypeFromEntity(RucsokEntity rucsokEntity) {
		return getRucsokTypeFromString(rucsokEntity.getVideoUrl());
	}

	public RucsokType getRucsokTypeFromString(String videoUrl) {
		RucsokType result = RucsokType.IMAGE;
		if (containsVideo(videoUrl)) {
			result = getVideoType(videoUrl);
		}
		return result;
	}

	private RucsokType getVideoType(String videoUrl) {
		RucsokType result = RucsokType.EMBEDVIDEO;
		if (endsWithCommonHTML5VideoType(videoUrl)) {
			result = RucsokType.HTML5VIDEO;
		}
		return result;
	}

	private boolean endsWithCommonHTML5VideoType(String videoUrl) {
		return isMp4(videoUrl) || isOgg(videoUrl) || isWebm(videoUrl);
	}

	private boolean isWebm(String videoUrl) {
		return videoUrl.toLowerCase().endsWith(WEBM);
	}

	private boolean isOgg(String videoUrl) {
		return videoUrl.toLowerCase().endsWith(OGG) || videoUrl.toLowerCase().endsWith(OGV);
	}

	private boolean isMp4(String videoUrl) {
		return videoUrl.toLowerCase().endsWith(MP4);
	}

	private boolean containsVideo(String videoUrl) {
		return null != videoUrl && videoUrl.length() > 0;
	}
}
