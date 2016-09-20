package com.rucsok.rucsok.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.rucsok.rucsok.service.helper.GeneralDocumentParser;

@RunWith(MockitoJUnitRunner.class)
public class GeneralDocumentParserTest {

	private static final String EMPTY_STRING = "";
	private static final String TEST_URL = "http://localhost:8080/images/rucsi.png";
	private static final String TEST_TITLE = "rucsok";

	private GeneralDocumentParser underTest;

	@Mock
	private Document document;

	@Before
	public void setUp() {
		underTest = new GeneralDocumentParser();
	}

	@Test
	public void getTitleShouldReturnCorrectTitleUrl_When_CallingDocumentTitle() {
		// Given
		when(document.title()).thenReturn(TEST_TITLE);
		// When
		String result = underTest.getTitle(document);
		// Then
		Assert.assertEquals("Result should equals", TEST_TITLE, result);
		verify(document).title();
	}
	
	@Test
	public void getTitleShouldReturnCorrectTitleUrl_When_CallingElementsAttr() {
		// Given
		Elements elements = Mockito.mock(Elements.class);
		when((elements).attr(GeneralDocumentParser.CONTENT)).thenReturn(TEST_TITLE);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_TITLE)).thenReturn( elements);
		// When
		String result = underTest.getTitle(document);
		// Then
		Assert.assertEquals("Result should equals", TEST_TITLE, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_TITLE);
		verify(elements).attr(GeneralDocumentParser.CONTENT);
	}
	
	@Test
	public void getImageUrlShouldReturnEmptyString_When_CallingElementsIsNull() {
		// Given
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_IMAGE)).thenReturn(null);
		// When
		String result = underTest.getImageUrl(document);
		// Then
		Assert.assertEquals("Result should equals", EMPTY_STRING, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_IMAGE);
	}

	@Test
	public void getImageUrlShouldReturnCorrectImageUrl_When_CallingElementsAttr() {
		// Given
		Elements elements = Mockito.mock(Elements.class);
		when((elements).attr(GeneralDocumentParser.CONTENT)).thenReturn(TEST_URL);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_IMAGE)).thenReturn( elements);
		// When
		String result = underTest.getImageUrl(document);
		// Then
		Assert.assertEquals("Result should equals", TEST_URL, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_IMAGE);
		verify(elements).attr(GeneralDocumentParser.CONTENT);
	}
	
	@Test
	public void getVideoUrlShouldReturnCorrectUrl_When_CallingElementsAttr() {
		// Given
		Elements elements = Mockito.mock(Elements.class);
		when((elements).attr(GeneralDocumentParser.CONTENT)).thenReturn(TEST_URL);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO)).thenReturn( elements);
		// When
		String result = underTest.getVideoUrl(document);
		// Then
		Assert.assertEquals("Result should equals", TEST_URL, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO);
		verify(elements).attr(GeneralDocumentParser.CONTENT);
	}
	
	@Test
	public void getVideoUrlShouldReturnCorrectUrl_When_HasVideoUrlButNotVideo() {
		// Given
		Elements elements = Mockito.mock(Elements.class);
		when((elements).attr(GeneralDocumentParser.CONTENT)).thenReturn(TEST_URL);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO)).thenReturn( null);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO_URL)).thenReturn( elements);
		// When
		String result = underTest.getVideoUrl(document);
		// Then
		Assert.assertEquals("Result should equals", TEST_URL, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO_URL);
		verify(elements).attr(GeneralDocumentParser.CONTENT);
	}
	
	@Test
	public void getVideoUrlShouldReturnCorrectEmptyString_When_HasNoVideo() {
		// Given
		Elements elements = Mockito.mock(Elements.class);
		when((elements).attr(GeneralDocumentParser.CONTENT)).thenReturn(TEST_URL);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO)).thenReturn( null);
		when(document.select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO_URL)).thenReturn( null);
		// When
		String result = underTest.getVideoUrl(document);
		// Then
		Assert.assertEquals("Result should equals", EMPTY_STRING, result);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO);
		verify(document).select(GeneralDocumentParser.META_PROPERTY_OG_VIDEO_URL);
	}
}
