package com.rucsok.rucsok;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.domain.RucsokType;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.view.controller.ListRucsokController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
@TestPropertySource("classpath:/application.properties")
public class ListRucsokControllerIntegrationTest {

	private static final String IMAGE = RucsokType.IMAGE.toString().toLowerCase();
	private static final String EMBED = RucsokType.EMBEDVIDEO.toString().toLowerCase();
	private static final String HTML5VIDEO = RucsokType.HTML5VIDEO.toString().toLowerCase();

	@Autowired
	private RucsokRepository rucsokDao;

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
	public void getShouldReturnStatusOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void getShouldReturnJson() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@Transactional
	public void getShouldContainsSameAmountOfDataThatCreatedInTheTestDatabase() throws Exception {
		// Given
		int numberOfItems = (int) rucsokDao.count();
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andDo(print())
				.andExpect((jsonPath("$", hasSize(numberOfItems))));
	}

	@Test
	@Transactional
	public void getShouldContainCorrectProperties() throws Exception {
		// Given
		List<RucsokEntity> allRucsok = rucsokDao.getAllRucsok();
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andDo(print())
				.andExpect(jsonPath("$[:1].id").value((int) allRucsok.get(0).getId()))
				.andExpect(jsonPath("$[:1].title").value(allRucsok.get(0).getTitle()))
				.andExpect(jsonPath("$[:1].imageUrl").value(allRucsok.get(0).getImageUrl()))
				.andExpect(jsonPath("$[:1].link").value(allRucsok.get(0).getLink()))
				// TODO this can't be checked this way
				// .andExpect(jsonPath("$[:1].videoUrl").value(isEmptyOrNullString()))
				.andExpect(jsonPath("$[:1].type").value(IMAGE))
				.andExpect(jsonPath("$[:1].username").value(allRucsok.get(0).getUser().getName()))

				.andExpect(jsonPath("$[1:2].id").value((int) allRucsok.get(1).getId()))
				.andExpect(jsonPath("$[1:2].title").value(allRucsok.get(1).getTitle()))
				.andExpect(jsonPath("$[1:2].videoUrl").value(allRucsok.get(1).getVideoUrl()))
				.andExpect(jsonPath("$[1:2].imageUrl").value(allRucsok.get(1).getImageUrl()))
				.andExpect(jsonPath("$[1:2].type").value(EMBED))

				.andExpect(jsonPath("$[2:3].id").value((int) allRucsok.get(2).getId()))
				.andExpect(jsonPath("$[2:3].title").value(allRucsok.get(2).getTitle()))
				.andExpect(jsonPath("$[2:3].videoUrl").value(allRucsok.get(2).getVideoUrl()))
				.andExpect(jsonPath("$[2:3].imageUrl").value(allRucsok.get(2).getImageUrl()))
				.andExpect(jsonPath("$[2:3].type").value(HTML5VIDEO))
				.andExpect(jsonPath("$[2:3].username").value(allRucsok.get(2).getUser().getName()));
	}
}
