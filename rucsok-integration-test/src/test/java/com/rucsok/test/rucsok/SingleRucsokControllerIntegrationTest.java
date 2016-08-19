package com.rucsok.test.rucsok;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.transform.RucsokTypeTransform;
import com.rucsok.rucsok.view.model.RucsokView;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class SingleRucsokControllerIntegrationTest {

	private static final int TEST_DATA_SIZE = 1;
	private static final int TEST_DATA_ID = 2;		
	
	private static final String REQUEST_MAPPING = "/rucsok/" + TEST_DATA_ID;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;
	
	@Autowired
	private RucsokDao rucsokDao;
	
	@Autowired
	private RucsokTypeTransform rucsokTypeTransform;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
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
	@Transactional
	public void contentShouldContainCorrectProperties() throws Exception {
		// Given	
		LocalDateTime createdAt = LocalDateTime.of(2016, 8, 8, 11, 11, 11);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RucsokView.DATEFORMAT);
		RucsokEntity rucsok = rucsokDao.findById(TEST_DATA_ID).get(1);
		String rucsokType = rucsokTypeTransform.getRucsokTypeFromEntity(rucsok).toString().toLowerCase();
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
				.andExpect(jsonPath("$.current.title", is(rucsok.getTitle())))
				.andExpect(jsonPath("$.current.link", is(rucsok.getLink())))
				.andExpect(jsonPath("$.current.type", is(rucsokType)))
				.andExpect(jsonPath("$.current.username", is(rucsok.getUser().getName())))
				.andExpect(jsonPath("$.previousId", is(1)))
				.andExpect(jsonPath("$.nextId", is(3)))
				.andExpect(jsonPath("$.current.createdAt", is(createdAt.format(formatter))));
	}
}
