package com.rucsok.rucsok.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.config.ListedRucsokServiceConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.repository.dao.ListedRucsokRepository;
import com.rucsok.rucsok.transform.DateTransformer;
import com.rucsok.rucsok.transform.ListedRucsokTransformer;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ListedRucsokServiceConfig.class, ListedRucsokService.class })
public class ListedRucsokServiceTest {

	private static final String TEST_START_DATE = "2016-08-30";
	private static final String TEST_END_DATE = "2016-08-31";
	private static final int TEST_DATA_SIZE = 1;
	private static final long TEST_ID_VALUE = 0L;
	private static final long TEST_UP_VOTE_NUMBER_VALUE = 10L;
	private static final long TEST_DOWN_VOTE_NUMBER_VALUE = -4L;
	private static final BigInteger TEST_ID = BigInteger.valueOf(TEST_ID_VALUE);
	private static final BigInteger TEST_UP_VOTE_NUMBER = BigInteger.valueOf(TEST_UP_VOTE_NUMBER_VALUE);
	private static final BigInteger TEST_DOWN_VOTE_NUMBER = BigInteger.valueOf(TEST_DOWN_VOTE_NUMBER_VALUE);
	private static final String TEST_DATE = "2016-08-30 11:11:15.0";
	private static final String TEST_DATE_DEFAULT = "2016-08-30T11:11:15"; // default
																			// toString
																			// value
	private static final String TEST_IMG_URL = "img";
	private static final String TEST_URL = "http://letscode.hu";
	private static final String TEST_TITLE = "Test";

	@Autowired
	private ListedRucsokRepository mockListedRucsokRepository;

	@Autowired
	private ListedRucsokTransformer mockListedRucsokTransformer;

	@Autowired
	private DateTransformer mockDateTransformer;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private ListedRucsokService underTest;

	@Test
	public void itShouldReturnRucsokWithCorrectData() {
		// Given
		LocalDate date = LocalDate.of(2016, 8, 30);
		List<Object[]> fakeObjects = createFakeObjects();
		List<Rucsok> fakeRucsoks = createFakeRucsoks();

		// When

		when(mockDateTransformer.getCurrentDay(date)).thenReturn(TEST_START_DATE);
		when(mockDateTransformer.getNextDay(date)).thenReturn(TEST_END_DATE);
		when(mockListedRucsokRepository.getHotRucsok(TEST_START_DATE, TEST_END_DATE,
				ListedRucsokService.HOT_PAGINATION_SIZE)).thenReturn(fakeObjects);
		when(mockListedRucsokTransformer.transformToRucsokList(fakeObjects)).thenReturn(fakeRucsoks);

		List<Rucsok> result = underTest.getListedRucsok(date);

		// Then

		Assert.assertEquals("Size", TEST_DATA_SIZE, result.size());
		Assert.assertEquals("Id", TEST_ID_VALUE, result.get(0).getId());
		Assert.assertEquals("Up Vote number", TEST_UP_VOTE_NUMBER_VALUE, result.get(0).getUpVote());
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER_VALUE, result.get(0).getDownVote());
		Assert.assertEquals("Title", TEST_TITLE, result.get(0).getTitle());
		Assert.assertEquals("Url", TEST_URL, result.get(0).getLink());
		Assert.assertEquals("Image url", TEST_IMG_URL, result.get(0).getImageUrl());
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.get(0).getCreatedAt().toString());

		verify(mockDateTransformer).getCurrentDay(date);
		verify(mockDateTransformer).getNextDay(date);
		verify(mockListedRucsokRepository).getHotRucsok(TEST_START_DATE, TEST_END_DATE,
				ListedRucsokService.HOT_PAGINATION_SIZE);
		verify(mockListedRucsokTransformer).transformToRucsokList(fakeObjects);

	}

	private List<Rucsok> createFakeRucsoks() {
		List<Rucsok> array = new ArrayList<>();
		array.add(createFakeRucsok());
		return array;
	}

	private Rucsok createFakeRucsok() {
		Rucsok result = new Rucsok();
		result.setId(TEST_ID_VALUE);
		result.setImageUrl(TEST_IMG_URL);
		result.setLink(TEST_URL);
		result.setTitle(TEST_TITLE);
		result.setCreatedAt(LocalDateTime.of(2016, 8, 30, 11, 11, 15));
		result.setUpVote(Long.valueOf(TEST_UP_VOTE_NUMBER_VALUE).intValue());
		result.setDownVote(Long.valueOf(TEST_DOWN_VOTE_NUMBER_VALUE).intValue());
		return result;
	}

	private List<Object[]> createFakeObjects() {
		List<Object[]> array = new ArrayList<>();
		array.add(createFakeObject());
		return array;
	}

	private Object[] createFakeObject() {
		Object[] object = new Object[7];
		object[0] = TEST_UP_VOTE_NUMBER;
		object[1] = TEST_DOWN_VOTE_NUMBER;
		object[2] = TEST_ID;
		object[3] = TEST_TITLE;
		object[4] = TEST_URL;
		object[5] = TEST_IMG_URL;
		object[6] = TEST_DATE;
		return object;
	}
}
