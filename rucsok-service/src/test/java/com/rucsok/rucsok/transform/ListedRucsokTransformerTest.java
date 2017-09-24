package com.rucsok.rucsok.transform;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.domain.RucsokType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ListedRucsokTransformer.class, RucsokTypeTransform.class })
public class ListedRucsokTransformerTest {

	private static final int TEST_LIST_SIZE = 2;
	private static final long TEST_ID_VALUE = 0L;
	private static final long TEST_UP_VOTE_NUMBER_VALUE = 10L;
	private static final long TEST_DOWN_VOTE_NUMBER_VALUE = -4L;
	private static final BigInteger TEST_ID = BigInteger.valueOf(TEST_ID_VALUE);
	private static final BigInteger TEST_UP_VOTE_NUMBER = BigInteger.valueOf(TEST_UP_VOTE_NUMBER_VALUE);
	private static final BigInteger TEST_DOWN_VOTE_NUMBER = BigInteger.valueOf(TEST_DOWN_VOTE_NUMBER_VALUE);
	private static final String TEST_DATE = "2016-08-30 11:11:15.0";
	private static final String TEST_DATE_DEFAULT = "2016-08-30T11:11:15"; // default toString value
	private static final String TEST_IMG_URL = "img";
	private static final String TEST_VIDEO_URL = "";
	private static final String TEST_URL = "http://letscode.hu";
	private static final String TEST_TITLE = "Test";
	private static final RucsokType TEST_TYPE = RucsokType.IMAGE;

	@Autowired
	private ListedRucsokTransformer underTest;
	
	@Test
	public void itShouldReturnListRucsokFromObjectArray() {
		// Given
		List<Object[]> object = createStubList();
		// When
		List<Rucsok> result = underTest.transformToRucsokList(object);
		// Then
		Assert.assertEquals("Size", TEST_LIST_SIZE, result.size());
		Assert.assertEquals("Id", TEST_ID_VALUE, result.get(0).getId());
		Assert.assertEquals("Up Vote number", TEST_UP_VOTE_NUMBER_VALUE, result.get(0).getUpVote());
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER_VALUE, result.get(0).getDownVote());
		Assert.assertEquals("Title", TEST_TITLE, result.get(0).getTitle());
		Assert.assertEquals("Url", TEST_URL, result.get(0).getLink());
		Assert.assertEquals("Video url", TEST_VIDEO_URL, result.get(0).getVideoUrl());
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.get(0).getCreatedAt().toString());
		Assert.assertEquals("Type", TEST_TYPE, result.get(0).getType());
		
		Assert.assertEquals("Id", TEST_ID_VALUE, result.get(1).getId());
		Assert.assertEquals("Up Vote number", TEST_UP_VOTE_NUMBER_VALUE, result.get(1).getUpVote());
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER_VALUE, result.get(1).getDownVote());
		Assert.assertEquals("Title", TEST_TITLE, result.get(1).getTitle());
		Assert.assertEquals("Url", TEST_URL, result.get(1).getLink());
		Assert.assertEquals("Image url", TEST_IMG_URL, result.get(1).getImageUrl());
		Assert.assertEquals("Video url", TEST_VIDEO_URL, result.get(1).getVideoUrl());
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.get(1).getCreatedAt().toString());
		Assert.assertEquals("Type", TEST_TYPE, result.get(1).getType());
	}
	

	@Test
	public void itShouldReturnRucsokFromObjectArray() {
		// Given
		Object[] object = createStub();
		// When
		Rucsok result = underTest.transformToRucsok(object);
		// Then
		Assert.assertEquals("Id", TEST_ID_VALUE, result.getId());
		Assert.assertEquals("Up Vote number", TEST_UP_VOTE_NUMBER_VALUE, result.getUpVote());
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER_VALUE, result.getDownVote());
		Assert.assertEquals("Title", TEST_TITLE, result.getTitle());
		Assert.assertEquals("Url", TEST_URL, result.getLink());
		Assert.assertEquals("Video url", TEST_VIDEO_URL, result.getVideoUrl());
		Assert.assertEquals("Image url", TEST_IMG_URL, result.getImageUrl());
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.getCreatedAt().toString());
		Assert.assertEquals("Type", TEST_TYPE, result.getType());
	}
	
	private List<Object[]> createStubList() {
		List<Object[]> array = new ArrayList<>();
		array.add(createStub());
		array.add(createStub());
		return array;
	}

	private Object[] createStub() {
		Object[] object = new Object[8];
		object[0] = TEST_UP_VOTE_NUMBER;
		object[1] = TEST_DOWN_VOTE_NUMBER;
		object[2] = TEST_ID;
		object[3] = TEST_TITLE;
		object[4] = TEST_URL;
		object[5] = TEST_IMG_URL;
		object[6] = TEST_VIDEO_URL;
		object[7] = TEST_DATE;
		return object;
	}
}
