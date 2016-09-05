package com.rucsok.test.rucsok.service.transform;

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
	
	private static final String EMPTY_STRING = "";
	@Autowired
	private RucsokTypeTransform underTest;

	@Test
	public void getRucsokTypeFromEntityShouldReturnImageWhenVideoUrlIsNull() {
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
	public void getRucsokTypeFromEntityShouldReturnImageWhenVideoUrlIsEmpty() {
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
	public void getRucsokTypeFromEntityShouldReturnEmbedWhenVideoUrlIsYoutube() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn("https://www.youtube.com/watch?v=buXwBr9H3VY");
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.EMBEDVIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5VideoWhenVideoUrlIsMp4() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn("http://i.imgur.com/ZSkyp8F.mp4");
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5VideoWhenVideoUrlIsOgg() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn("http://techslides.com/demos/sample-videos/small.ogv");
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
	
	@Test
	public void getRucsokTypeFromEntityShouldReturnHtml5VideoWhenVideoUrlIsWebm() {
		// Given
		RucsokEntity rucsok = Mockito.mock(RucsokEntity.class);
		when(rucsok.getVideoUrl()).thenReturn("http://techslides.com/demos/sample-videos/small.webm");
		// When
		RucsokType result = underTest.getRucsokTypeFromEntity(rucsok);
		// Then
		Assert.assertEquals(RucsokType.HTML5VIDEO, result);
		verify(rucsok).getVideoUrl();
	}
}
