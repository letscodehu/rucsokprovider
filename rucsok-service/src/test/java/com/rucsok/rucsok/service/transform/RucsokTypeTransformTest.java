package com.rucsok.rucsok.service.transform;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.domain.RucsokType;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.transform.RucsokTypeTransform;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RucsokTypeTransform.class })
public class RucsokTypeTransformTest {
	
	private static final String WEBM_URL = "http://techslides.com/demos/sample-videos/small.webm";
	private static final String OGV_URL = "http://techslides.com/demos/sample-videos/small.ogv";
	private static final String IMGUR_URL = "http://i.imgur.com/ZSkyp8F.mp4";
	private static final String YOUTUBE_URL = "https://www.youtube.com/watch?v=buXwBr9H3VY";
	private static final String EMPTY_STRING = "";
	@Autowired
	private RucsokTypeTransform underTest;

	@Test
	public void getRucsokTypeFromEntityShouldReturnImage_When_VideoUrlIsNull() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(null);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.IMAGE, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnImage_When_VideoUrlIsEmpty() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(EMPTY_STRING);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.IMAGE, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnEmbed_When_VideoUrlIsYoutube() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(YOUTUBE_URL);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.EMBEDVIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5Video_When_VideoUrlIsMp4() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(IMGUR_URL);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5Video_When_VideoUrlIsOgg() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(OGV_URL);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5Video_When_VideoUrlIsWebm() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn(WEBM_URL);
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
}
