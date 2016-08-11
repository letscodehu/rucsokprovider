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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.helper.UrlFetchHelper;
import com.rucsok.rucsok.view.controller.ListRucsokController;
import com.rucsok.test.config.RepositoryConfig;
import com.rucsok.test.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfig.class, TestConfig.class})
@WebIntegrationTest
public class ListRucsokControllerIntegrationTest {

	@Autowired
	private RucsokDao rucsokDao;

	@Autowired
	private ListRucsokController listRucsokController;	
	
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
		int numberOfItems = (int) rucsokDao.count();
		// When
		// Then
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING)).andDo(print())
				.andExpect((jsonPath("$", hasSize(numberOfItems))));
	}

	@Test
	public void contentShouldContainCorrectProperties() throws Exception {
		// Given
		List<RucsokEntity> allRucsok = rucsokDao.getAllRucsok();
		// When
		// Then
		int lastIndex = allRucsok.size()-1;
		mockMvc.perform(get(ListRucsokController.REQUEST_MAPPING))
				.andDo(print())
				.andExpect(jsonPath("$[:1].id[0]", is((int) allRucsok.get(0).getId())))
				.andExpect(jsonPath("$[:1].title[0]", is(allRucsok.get(0).getTitle())))
				.andExpect(jsonPath("$[:1].imageUrl[0]", is(allRucsok.get(0).getImageUrl())))
				.andExpect(jsonPath("$[:1].link[0]", is(allRucsok.get(0).getLink())))
				.andExpect(jsonPath("$[:1].videoUrl[0]", isEmptyOrNullString()))
				.andExpect(jsonPath("$[-1:].id[0]", is((int) allRucsok.get(lastIndex).getId())))
				.andExpect(jsonPath("$[-1:].title[0]", is(allRucsok.get(lastIndex).getTitle())))
				.andExpect(jsonPath("$[-1:].videoUrl[0]", is(allRucsok.get(lastIndex).getVideoUrl())))
				.andExpect(jsonPath("$[-1:].imageUrl[0]", is(allRucsok.get(lastIndex).getImageUrl())));
	}
}
