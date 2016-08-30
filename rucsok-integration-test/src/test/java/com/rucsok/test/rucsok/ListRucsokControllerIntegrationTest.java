package com.rucsok.test.rucsok;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.rucsok.domain.RucsokType;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.view.controller.ListRucsokController;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
@TestPropertySource("classpath:/application.properties")
public class ListRucsokControllerIntegrationTest {

	private static final String IMAGE = RucsokType.IMAGE.toString().toLowerCase();
	private static final String EMBED = RucsokType.EMBEDVIDEO.toString().toLowerCase();
	private static final String HTML5VIDEO = RucsokType.HTML5VIDEO.toString().toLowerCase();

	@Autowired
	private RucsokDao rucsokDao;
	
	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(SecurityMockMvcConfigurers.springSecurity())
				.build();
	}

	@Test
	@Transactional
	public void statusShouldOk() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void contentShouldBeJson() throws Exception {
		// Given
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING))
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	@Transactional
	public void contentShouldContainsSameAmountOfDataThatCreatedInTheTestDatabase() throws Exception {
		// Given
		int numberOfItems = (int) rucsokDao.count();
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andDo(print())
				.andExpect((jsonPath("$", hasSize(numberOfItems))));
	}

	@Test
	@Transactional
	public void contentShouldContainCorrectProperties() throws Exception {
		// Given
		List<RucsokEntity> allRucsok = rucsokDao.getAllRucsok();
		System.out.println(allRucsok.get(1).getId());
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING))
				.andDo(print())
				.andExpect(jsonPath("$[:1].id[0]", is((int) allRucsok.get(0).getId())))
				.andExpect(jsonPath("$[:1].title[0]", is(allRucsok.get(0).getTitle())))
				.andExpect(jsonPath("$[:1].imageUrl[0]", is(allRucsok.get(0).getImageUrl())))
				.andExpect(jsonPath("$[:1].link[0]", is(allRucsok.get(0).getLink())))
				.andExpect(jsonPath("$[:1].videoUrl[0]", isEmptyOrNullString()))
				.andExpect(jsonPath("$[:1].type[0]", is(IMAGE)))
				.andExpect(jsonPath("$[:1].username[0]", is(allRucsok.get(0).getUser().getName())))
				.andExpect(jsonPath("$[:2].id[1]", is((int) allRucsok.get(1).getId())))
				.andExpect(jsonPath("$[:2].title[1]", is(allRucsok.get(1).getTitle())))
				.andExpect(jsonPath("$[:2].videoUrl[1]", is(allRucsok.get(1).getVideoUrl())))
				.andExpect(jsonPath("$[:2].imageUrl[1]", is(allRucsok.get(1).getImageUrl())))
				.andExpect(jsonPath("$[:2].type[1]", is(EMBED)))
				.andExpect(jsonPath("$[:3].id[2]", is((int) allRucsok.get(2).getId())))
				.andExpect(jsonPath("$[:3].title[2]", is(allRucsok.get(2).getTitle())))
				.andExpect(jsonPath("$[:3].videoUrl[2]", is(allRucsok.get(2).getVideoUrl())))
				.andExpect(jsonPath("$[:3].imageUrl[2]", is(allRucsok.get(2).getImageUrl())))
				.andExpect(jsonPath("$[:3].type[2]", is(HTML5VIDEO)))
				.andExpect(jsonPath("$[:3].username[2]", is(allRucsok.get(2).getUser().getName())));
	}
}
