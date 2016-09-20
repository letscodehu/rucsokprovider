package com.rucsok.rucsok;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.service.RucsokService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class ListNewRucsokControllerIntegrationTest {

	private static final String REQUEST_MAPPING_PREFIX = "/rucsok/new/";
	private static final int START_PAGE_NUMBER = 0;
	private static final String REQUEST_MAPPING = REQUEST_MAPPING_PREFIX + START_PAGE_NUMBER;
	
	@Value("${rucsok.fresh.page.size}")
	public int FRESH_PAGINATION_SIZE;

	@Value("${rucsok.hot.page.size}")
	public int HOT_PAGINATION_SIZE;

	@Autowired
	private RucsokService rucsokService;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@Test
	@Transactional
	public void getShouldReturnOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void getShouldReturnJson() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@Transactional
	public void getShouldContainsSameAmountOfDataThatCreatedInTheTestDatabase() throws Exception {
		// Given
		int numberOfItems = FRESH_PAGINATION_SIZE;
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect((jsonPath("$", hasSize(numberOfItems))));
	}

	@Test
	@Transactional
	public void getShouldContainCorrectProperties() throws Exception {
		// Given
		List<Rucsok> rucsoks = rucsokService.findFresh(START_PAGE_NUMBER);
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING)).andExpect(jsonPath("$[:1].id").value((int) rucsoks.get(0).getId()))

				.andExpect(jsonPath("$[1:2].id").value((int) rucsoks.get(1).getId()))

				.andExpect(jsonPath("$[2:3].id").value((int) rucsoks.get(2).getId()));
	}

	@Test
	@Transactional
	public void getShouldContainCorrectPropertiesOnSecondPage() throws Exception {
		// Given
		int page = 1;
		List<Rucsok> rucsoks = rucsokService.findFresh(page);
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_PREFIX + page))
				.andExpect(jsonPath("$[:1].id").value((int) rucsoks.get(0).getId()))

				.andExpect(jsonPath("$[1:2].id").value((int) rucsoks.get(1).getId()))

				.andExpect(jsonPath("$[2:3].id").value((int) rucsoks.get(2).getId()));
	}

	@Test
	@Transactional
	public void getShouldReturnEmptyDataWhenPageNumberLargerThanDataSet() throws Exception {	
		// Given
		int page = 100;
		// When
		// Then
		mockMvc.perform(get(REQUEST_MAPPING_PREFIX + page))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$").isEmpty());
	}

}
