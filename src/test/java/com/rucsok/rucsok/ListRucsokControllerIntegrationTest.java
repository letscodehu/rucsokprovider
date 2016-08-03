package com.rucsok.rucsok;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import org.hamcrest.Matchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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

import com.rucsok.rucsok.view.controller.ListRucsokController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@WebIntegrationTest
public class ListRucsokControllerIntegrationTest {

	private static final int TEST_DATA_SIZE = 3;

	@Autowired
	private ListRucsokController listRucsokController;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(listRucsokController).build();
	}

	@Test
	public void statusShouldOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	public void contentShouldBeJson() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	public void contentShouldContainsSameAmountOfDataThatCreatedInTheTestDatabase() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andExpect((jsonPath("$", hasSize(TEST_DATA_SIZE))));
	}

	@Test
	public void contentShouldContainCorrectProperties() throws Exception {
		// Given		
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andExpect(jsonPath("$[0].title", is("rucsok01")))
				.andExpect(jsonPath("$[0].link", is("http://rucsok.com/01.gif")));
	}
}
