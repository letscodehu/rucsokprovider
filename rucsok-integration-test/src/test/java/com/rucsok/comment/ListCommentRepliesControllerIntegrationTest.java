package com.rucsok.comment;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class ListCommentRepliesControllerIntegrationTest {
	
	private static final String REQUEST_MAPPING_FIRST_PAGE = "/comment/8/replies/0";
	private static final String REQUEST_MAPPING_LAST_PAGE = "/comment/8/replies/1";
	
	private static final int totalPages = 2;
	private static final int totalElements = 4;
	
	@Value("${comment.replies.page.size}")
	private int pageSize;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}
	
	@Test
	public void getShouldReturnStatusOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_FIRST_PAGE)).andExpect(status().isOk());
	}

	@Test
	public void itShouldReturnJSON() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_FIRST_PAGE))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void getShouldContainCorrectData_When_FirstPageLoaded() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_FIRST_PAGE))
				.andExpect(jsonPath("$.content", hasSize(pageSize)))
				.andExpect(jsonPath("$.totalElements", is(totalElements)))
				.andExpect(jsonPath("$.totalPages", is(totalPages)))
				.andExpect(jsonPath("$.last", is(false)))
				.andExpect(jsonPath("$.content[:1].text").value("rucsok forever"))
				.andExpect(jsonPath("$.content[:1].username").value("kecske"))
				.andExpect(jsonPath("$.content[:1].date").value("2016.08.23 12:12:15"))
				.andExpect(jsonPath("$.content[1:2].text").value("rucsok begins"))
				.andExpect(jsonPath("$.content[1:2].username").value("kecske"))
				.andExpect(jsonPath("$.content[1:2].date").value("2016.08.23 13:12:15"));
					
	}
	
	@Test
	public void getShouldContainCorrectData_When_LastPageLoaded() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_LAST_PAGE))
				.andExpect(jsonPath("$.content", hasSize(pageSize)))
				.andExpect(jsonPath("$.totalElements", is(totalElements)))
				.andExpect(jsonPath("$.totalPages", is(totalPages)))
				.andExpect(jsonPath("$.last", is(true)))
				.andExpect(jsonPath("$.content[:1].text").value("rucsok knight"))
				.andExpect(jsonPath("$.content[:1].username").value("kecske"))
				.andExpect(jsonPath("$.content[:1].date").value("2016.08.23 13:14:15"))
				.andExpect(jsonPath("$.content[1:2].text").value("rucsok rises"))
				.andExpect(jsonPath("$.content[1:2].username").value("rucsok"))
				.andExpect(jsonPath("$.content[1:2].date").value("2016.08.24 13:14:15"));
						
	}
	
}
