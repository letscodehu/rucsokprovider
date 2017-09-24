package com.rucsok.rucsok.service;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RucsokCrawlServiceConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.RucsokCrawlerService;
import com.rucsok.rucsok.service.helper.GeneralDocumentParser;
import com.rucsok.rucsok.service.helper.RucsokCrawlHelper;
import com.rucsok.rucsok.service.helper.UrlFetchHelper;
import com.rucsok.rucsok.transform.RucsokTypeTransform;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.transform.UserTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RucsokCrawlerService.class, RucsokCrawlHelper.class, GeneralDocumentParser.class,
		RucsokCrawlServiceConfig.class, RucsokTypeTransform.class, UserTransformer.class })
public class RucsokCrawlerServiceIntegrationTest implements ApplicationContextAware {
	
	private static final String TEST_USERNAME = "rucsok";

	private static final String TEST_PAGE_IMGUR = "src/main/resources/test/html-pages/imgur.html";
	private static final String TEST_PAGE_YOUTUBE = "src/main/resources/test/html-pages/youtube.html";

	@Autowired
	private RucsokCrawlerService underTest;

	private UrlFetchHelper urlFetcher;
	private UserCheckerService userService;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		urlFetcher = context.getBean(UrlFetchHelper.class);
		userService = context.getBean(UserCheckerService.class);
	}

	@Before
	public void setUp() throws IOException {
	}
	
	@Test
	public void testYoutubeCrawlShouldReturnCorrectRucsok() throws IOException {
		// Given
		String url = "http://youtube";
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		when(urlFetcher.fetchUrl(url)).thenReturn(getDocumentFromHtmlString(readHtmlFromFile(TEST_PAGE_YOUTUBE)));
		when(userService.isUserExists(TEST_USERNAME)).thenReturn(true);
		when(userService.findUserByName(TEST_USERNAME)).thenReturn(userEntity);
		when(userEntity.getEmail()).thenReturn("rucsok@letscode.hu");
		when(userEntity.getName()).thenReturn("rucsok");
		// When
		Rucsok result = underTest.crawl(url, TEST_USERNAME);
		// Then
		Assert.assertEquals("Image", "https://i.ytimg.com/vi/VIep9sqtQSY/hqdefault.jpg", result.getImageUrl());
		Assert.assertEquals("Title", "Project fit interviews", result.getTitle());
		Assert.assertEquals("Video", "https://www.youtube.com/embed/VIep9sqtQSY", result.getVideoUrl());
		Assert.assertEquals("Link", url, result.getLink());
	}

	@Test
	public void testImgurCrawlShouldReturnCorrectRucsok() throws IOException {
		// Given
		String url = "http://imgur";
		UserEntity userEntity = Mockito.mock(UserEntity.class);
		when(urlFetcher.fetchUrl(url)).thenReturn(getDocumentFromHtmlString(readHtmlFromFile(TEST_PAGE_IMGUR)));
		when(userService.isUserExists(TEST_USERNAME)).thenReturn(true);
		when(userService.findUserByName(TEST_USERNAME)).thenReturn(userEntity);
		when(userEntity.getEmail()).thenReturn("rucsok@letscode.hu");
		when(userEntity.getName()).thenReturn("rucsok");
		// When
		Rucsok result = underTest.crawl(url, TEST_USERNAME);
		// Then
		Assert.assertEquals("Image", "http://i.imgur.com/bio5rS3.jpg?fb", result.getImageUrl());
		Assert.assertEquals("Title", "Home Design Styles (Pixels!)", result.getTitle());
		Assert.assertEquals("Link", url, result.getLink());
	}
	
	private Optional<Document> getDocumentFromHtmlString(String html){
		Document doc = Jsoup.parse(html);
		return Optional.of(doc);
	}

	private String readHtmlFromFile(String fileName) throws IOException {
		StringBuilder sb = new StringBuilder();
		Files.lines(Paths.get(fileName), StandardCharsets.UTF_8).forEach(s -> sb.append(s));
		return sb.toString();
	}
}
