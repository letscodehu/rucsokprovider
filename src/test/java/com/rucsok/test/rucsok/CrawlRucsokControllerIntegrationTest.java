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

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
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
import com.rucsok.rucsok.service.RucsokCrawlerService;
import com.rucsok.rucsok.view.controller.CrawlRucsokController;
import com.rucsok.rucsok.view.model.RucsokCheckRequest;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class CrawlRucsokControllerIntegrationTest {

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

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
		
		rucsokService = Mockito.mock(RucsokCrawlerService.class);
		crawlRucsokController.setRucsokService(rucsokService);
		mapper = new ObjectMapper();
	}

	@Test
	@WithUserDetails("rucsok")
	public void crawlerShouldReturnCorrectCrawledObject() throws Exception {

		// Given

		RucsokCheckRequest request = new RucsokCheckRequest();
		request.setUrl(TEST_URL);
		Rucsok rucsok = Mockito.mock(Rucsok.class);

		// When

		when(rucsok.getImageUrl()).thenReturn(MOCK_IMAGE);
		when(rucsok.getLink()).thenReturn(MOCK_LINK);
		when(rucsok.getTitle()).thenReturn(MOCK_TITLE);
		when(rucsokService.crawl(TEST_URL)).thenReturn(rucsok);

		mockMvc.perform(post(CrawlRucsokController.REQUEST_MAPPING)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.title", is(MOCK_TITLE)))
				.andExpect(jsonPath("$.link", is(MOCK_LINK)))
				.andExpect(jsonPath("$.imageUrl", is(MOCK_IMAGE)))
				.andExpect(jsonPath("$.videoUrl", isEmptyOrNullString()));

		// Then

		verify(rucsokService).crawl(TEST_URL);
		verify(rucsok).getImageUrl();
		verify(rucsok).getLink();
		verify(rucsok).getTitle();

	}
	

	@Test
	public void crawlerShouldReturnFoundWhenUserNotLoggedIn() throws Exception {

		// Given

		// When

		// Then
		
		mockMvc.perform(post(CrawlRucsokController.REQUEST_MAPPING)
				.with(csrf())
				.contentType(MediaType.APPLICATION_JSON)
				.content(""))
				.andExpect(status().isFound());				

	}

}
