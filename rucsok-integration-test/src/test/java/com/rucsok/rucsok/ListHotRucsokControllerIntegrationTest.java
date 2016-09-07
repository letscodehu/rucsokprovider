package com.rucsok.rucsok;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.service.ListedRucsokService;
import com.rucsok.rucsok.view.controller.ListHotRucsokController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class ListHotRucsokControllerIntegrationTest {
	
	private static final String REQUEST_MAPPING = ListHotRucsokController.REQUEST_MAPPING;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@Test
	public void statusShouldOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("date", "2016-08-30")
				.param("date", "2016-08-30"))
				.andExpect(status().isOk());
	}

	@Test
	public void contentShouldBeJson() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("date", "2016-08-30")
				.param("date", "2016-08-30"))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void contentShouldContainsSameAmountOfDataThatCreatedInTheTestDatabase() throws Exception {
		// Given
		int numberOfItems = ListedRucsokService.HOT_PAGINATION_SIZE;
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("date", "2016-08-30")
				.param("date", "2016-08-30"))
				.andExpect((jsonPath("$", hasSize(numberOfItems))))
				.andExpect(jsonPath("$[:1].id").value(9))  // id's from the integration-test.sql
				.andExpect(jsonPath("$[1:2].id").value(8))
				.andExpect(jsonPath("$[2:3].id").value(7));
	}

}
