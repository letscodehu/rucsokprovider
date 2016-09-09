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
public class ListCommentControllerIntegrationTest {
	
	private static final String REQUEST_MAPPING_FIRST_PAGE = "/rucsok/1/comment/0";
	private static final String REQUEST_MAPPING_LAST_PAGE = "/rucsok/1/comment/2";
	
	private static final int totalPages = 3;
	private static final int totalElements = 6;
	
	@Value("${comment.page.size}")
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
	public void itShouldReturnStatusOk() throws Exception {
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
	public void itShouldContainCorrectData_When_FirstPageLoaded() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_FIRST_PAGE))
				.andExpect(jsonPath("$.content", hasSize(pageSize)))
				.andExpect(jsonPath("$.totalElements", is(totalElements)))
				.andExpect(jsonPath("$.totalPages", is(totalPages)))
				.andExpect(jsonPath("$.last", is(false)))
				.andExpect(jsonPath("$.content[:1].text").value("rucsok like no tomorrow"))
				.andExpect(jsonPath("$.content[:1].username").value("rucsok"))
				.andExpect(jsonPath("$.content[:1].date").value("2016.08.08 12:12:12"))
				.andExpect(jsonPath("$.content[1:2].text").value("gonna delete this"))
				.andExpect(jsonPath("$.content[1:2].username").value("kecske"))
				.andExpect(jsonPath("$.content[1:2].date").value("2016.08.08 15:15:15"));
					
						
	}
	
	@Test
	public void itShouldContainCorrectData_When_LastPageLoaded() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_LAST_PAGE))
				.andExpect(jsonPath("$.content", hasSize(pageSize)))
				.andExpect(jsonPath("$.totalElements", is(totalElements)))
				.andExpect(jsonPath("$.totalPages", is(totalPages)))
				.andExpect(jsonPath("$.last", is(true)))
				.andExpect(jsonPath("$.content[:1].text").value("rucs it up3 best sequel"))
				.andExpect(jsonPath("$.content[:1].username").value("rucsok"))
				.andExpect(jsonPath("$.content[:1].date").value("2016.08.20 12:12:15"))
				.andExpect(jsonPath("$.content[1:2].text").value("rucsok and robin"))
				.andExpect(jsonPath("$.content[1:2].username").value("kecske"))
				.andExpect(jsonPath("$.content[1:2].date").value("2016.08.21 12:12:15"));
					
						
	}
	
}
