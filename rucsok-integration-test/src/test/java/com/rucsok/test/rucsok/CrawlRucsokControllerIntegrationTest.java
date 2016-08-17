package com.rucsok.test.rucsok;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.RucsokType;
import com.rucsok.rucsok.service.RucsokCrawlerService;
import com.rucsok.rucsok.view.controller.CrawlRucsokController;
import com.rucsok.rucsok.view.model.RucsokCheckRequest;
import com.rucsok.test.TokenHelper;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;
import com.rucsok.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class CrawlRucsokControllerIntegrationTest {

	private static final String TEST_USERNAME = "rucsok";

	private static final String MOCK_USERNAME = "darudesandstorm";
	
	private static final String MOCK_TITLE = "rucsokTitle";

	private static final String MOCK_LINK = "rucsokLink";

	private static final String MOCK_IMAGE = "mockImage";

	private static final String TEST_URL = "http://test.test";
	
	@Autowired
	private WebApplicationContext context;

	@Autowired
	private CrawlRucsokController crawlRucsokController;

	private RucsokCrawlerService rucsokService;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	private String accessToken;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		rucsokService = Mockito.mock(RucsokCrawlerService.class);
		crawlRucsokController.setRucsokService(rucsokService);
		mapper = new ObjectMapper();
		accessToken = TokenHelper.getAccessToken("rucsok", "123", mockMvc);
	}

	@Test
	@WithUserDetails(TEST_USERNAME)
	public void crawlerShouldReturnCorrectCrawledObject() throws Exception {

		// Given

		RucsokCheckRequest request = new RucsokCheckRequest();
		request.setUrl(TEST_URL);
		Rucsok rucsok = Mockito.mock(Rucsok.class);
		User user = Mockito.mock(User.class);
		
		// When

		when(rucsok.getImageUrl()).thenReturn(MOCK_IMAGE);
		when(rucsok.getLink()).thenReturn(MOCK_LINK);
		when(rucsok.getTitle()).thenReturn(MOCK_TITLE);
		when(rucsok.getVideoUrl()).thenReturn(null);
		when(rucsok.getType()).thenReturn(RucsokType.IMAGE);
		when(rucsok.getUser()).thenReturn(user);
		when(rucsok.getCreatedAt()).thenReturn(LocalDateTime.now());
		when(user.getUsername()).thenReturn(MOCK_USERNAME);
		when(rucsokService.crawl(TEST_URL, TEST_USERNAME)).thenReturn(rucsok);

		mockMvc.perform(post(CrawlRucsokController.REQUEST_MAPPING)
				.header("Authorization", "Bearer " + accessToken)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is(MOCK_TITLE)))
				.andExpect(jsonPath("$.username", is(MOCK_USERNAME)))
				.andExpect(jsonPath("$.link", is(MOCK_LINK)))
				.andExpect(jsonPath("$.imageUrl", is(MOCK_IMAGE)))
				.andExpect(jsonPath("$.videoUrl", isEmptyOrNullString()));

		// Then

		verify(rucsokService).crawl(TEST_URL, TEST_USERNAME);
		verify(rucsok).getImageUrl();
		verify(rucsok).getLink();
		verify(rucsok).getTitle();
		verify(rucsok).getVideoUrl();
		verify(rucsok).getType();
		verify(rucsok).getUser();
		verify(user).getUsername();

	}
	

	@Test
	@Ignore
	public void crawlerShouldReturnUnauthorizedWhenUserNotLoggedIn() throws Exception {

		// Given

		// When

		// Then
		
		mockMvc.perform(post(CrawlRucsokController.REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
				.andExpect(status().isUnauthorized());				

	}

}
