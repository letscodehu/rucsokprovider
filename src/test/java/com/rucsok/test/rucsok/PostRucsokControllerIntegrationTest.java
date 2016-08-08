package com.rucsok.test.rucsok;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Assert;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rucsok.rucsok.repository.dao.RucsokDao;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.view.controller.PostRucsokController;
import com.rucsok.rucsok.view.model.RucsokInsertRequest;
import com.rucsok.rucsok.view.model.RucsokPost;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@WebIntegrationTest
public class PostRucsokControllerIntegrationTest {

	private static final String TEST_TITLE = "epic rucsok";

	private static final String TEST_IMAGE = "http://rucsok.com/rucsok.jpg";

	private static final String TEST_URL = "http://rucsok.com/epic-rucsok";

	private static final String TEST_UNIQUE_URL = "http://rucsok.com/epic-url-rucsok";

	@Autowired
	private PostRucsokController postRucsokController;

	@Autowired
	private RucsokDao rucsokDao;

	private MockMvc mockMvc;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(postRucsokController).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void postShouldReturnBadGatewayWhenRequestIsNull() throws Exception {

		// Given

		// When

		// Then

		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(null))).andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldReturnBadGatewayWhenLinkIsNull() throws Exception {

		// Given
		
		RucsokPost rucsok = createRucsokPostHelper();
		rucsok.setLink(null);
		RucsokInsertRequest request = createRucsokInsertRequestHelper(rucsok);
		
		// When

		// Then
		
		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(status().isBadRequest());

	}
	
	@Test
	public void postShouldReturnBadGatewayWhenImageIsNull() throws Exception {

		// Given
		
		RucsokPost rucsok = createRucsokPostHelper();
		rucsok.setImageUrl(null);
		rucsok.setLink("http://asdasdasd.asd");
		RucsokInsertRequest request = createRucsokInsertRequestHelper(rucsok);
		
		// When

		// Then
		
		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(status().isBadRequest());

	}

	@Test
	public void postShouldSaveTheNewEntity() throws Exception {

		// Given

		RucsokPost rucsok = createRucsokPostHelper();
		RucsokInsertRequest request = createRucsokInsertRequestHelper(rucsok);

		// When

		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(status().isCreated());

		RucsokEntity entity = rucsokDao.findByLink(TEST_URL);

		// Then

		Assert.assertNotNull("Entity should'nt be null", entity);
		Assert.assertEquals("Link should match", entity.getLink(), rucsok.getLink());
		Assert.assertEquals("Image should match", entity.getImageUrl(), rucsok.getImageUrl());
		Assert.assertEquals("Title should match", entity.getTitle(), rucsok.getTitle());
	}

	@Test
	public void postShouldNotSaveTheSameUrlAgain() throws Exception {

		// Given

		RucsokPost rucsok = createRucsokPostHelper();
		rucsok.setLink(TEST_UNIQUE_URL);
		RucsokInsertRequest request = createRucsokInsertRequestHelper(rucsok);

		// When

		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(status().isCreated());

		mockMvc.perform(post(PostRucsokController.REQUEST_MAPPING).contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(request))).andExpect(status().isConflict());

		RucsokEntity entity = rucsokDao.findByLink(TEST_UNIQUE_URL);

		// Then

		Assert.assertNotNull("Entity should'nt be null", entity);
	}

	private RucsokInsertRequest createRucsokInsertRequestHelper(RucsokPost rucsok) {
		RucsokInsertRequest request = new RucsokInsertRequest();
		request.setRucsok(rucsok);
		return request;
	}

	private RucsokPost createRucsokPostHelper() {
		String link = TEST_URL;
		String image = TEST_IMAGE;
		String title = TEST_TITLE;

		RucsokPost rucsok = new RucsokPost();
		rucsok.setImageUrl(image);
		rucsok.setLink(link);
		rucsok.setTitle(title);
		return rucsok;
	}
}
