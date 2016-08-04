package com.rucsok.test.rucsok;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rucsok.rucsok.view.controller.SingleRucsokController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@WebIntegrationTest
public class SingleRucsokControllerIntegrationTest {

	private static final int TEST_DATA_SIZE = 1;
	
	private static final String REQUEST_MAPPING = "/rucsok/2";

	@Autowired
	private SingleRucsokController singleRucsokController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(singleRucsokController).build();
	}

	@Test
	public void statusShouldOk() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	public void contentShouldBeJson() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void contentShouldContainCorrectProperties() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
				.andExpect(jsonPath("$.current.title", is("rucsok02")))
				.andExpect(jsonPath("$.current.link", is("http://rucsok.com/02.gif")))
				.andExpect(jsonPath("$.previousId", is(1)))
				.andExpect(jsonPath("$.nextId", is(3)));
	}
}
