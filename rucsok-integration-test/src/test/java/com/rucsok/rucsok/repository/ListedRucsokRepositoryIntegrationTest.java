package com.rucsok.rucsok.repository;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.repository.dao.ListedRucsokRepository;
import com.rucsok.rucsok.repository.dao.VoteDao;
import com.rucsok.rucsok.repository.domain.VoteEntity;
import com.rucsok.rucsok.repository.domain.VotePK;
import com.rucsok.rucsok.repository.domain.VoteTypeEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class ListedRucsokRepositoryIntegrationTest {

	private static final String END_DATE = "2016-08-31";
	private static final String START_DATE = "2016-08-30";
	private static final int TEST_LIMIT = 1;
	private static final long TEST_ID_VALUE = 9L; // the last posted rucsok between
													// the start and test date
	private static final long TEST_UP_VOTE_NUMBER_VALUE = 0L;
	private static final long TEST_DOWN_VOTE_NUMBER_VALUE = 0L;
	private static final BigInteger TEST_ID = BigInteger.valueOf(TEST_ID_VALUE);
	private static final BigInteger TEST_UP_VOTE_NUMBER = BigInteger.valueOf(TEST_UP_VOTE_NUMBER_VALUE);
	private static final BigInteger TEST_DOWN_VOTE_NUMBER = BigInteger.valueOf(TEST_DOWN_VOTE_NUMBER_VALUE);
	private static final String TEST_DATE_DEFAULT = "2016-08-30 11:11:15.0";
	private static final String TEST_IMG_URL = "img05";
	private static final String TEST_VIDEO_URL = null;
	private static final String TEST_URL = "http://rucsok.com/05.gif";
	private static final String TEST_TITLE = "justforpagination5";

	@Autowired
	private ListedRucsokRepository listedRucsokRepository;

	@Autowired
	private VoteDao voteRepository;

	@Test
	@Transactional
	public void itShouldReturnTheFirstRucsokBetweenTheDates() {
		// Given

		// When

		List<Object[]> result = listedRucsokRepository.getHotRucsok(START_DATE, END_DATE, TEST_LIMIT);

		// Then

		Assert.assertEquals("Size", 1, result.size());
		Assert.assertEquals("Up Vote number", TEST_UP_VOTE_NUMBER, result.get(0)[0]);
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER, result.get(0)[1]);
		Assert.assertEquals("Id", TEST_ID, result.get(0)[2]);
		Assert.assertEquals("Title", TEST_TITLE, result.get(0)[3]);
		Assert.assertEquals("Url", TEST_URL, result.get(0)[4]);
		Assert.assertEquals("Image url", TEST_IMG_URL, result.get(0)[5]);
		Assert.assertEquals("Video url", TEST_VIDEO_URL, result.get(0)[6]);
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.get(0)[7].toString());
	}

	@Test
	@Transactional
	public void itShouldIncreaseTheVoteNumberWithOne_When_UpvotedOnce() {
		// Given

		createAVote(VoteTypeEntity.UP, TEST_ID.intValue(), 1);

		BigInteger newVoteValue = TEST_UP_VOTE_NUMBER.add(new BigInteger("1"));

		// When

		List<Object[]> result = listedRucsokRepository.getHotRucsok(START_DATE, END_DATE, TEST_LIMIT);

		// Then

		Assert.assertEquals("Size", 1, result.size());
		Assert.assertEquals("Vote number", newVoteValue, result.get(0)[0]);
		Assert.assertEquals("Down Vote number", TEST_DOWN_VOTE_NUMBER, result.get(0)[1]);
		Assert.assertEquals("Id", TEST_ID, result.get(0)[2]);
		Assert.assertEquals("Title", TEST_TITLE, result.get(0)[3]);
		Assert.assertEquals("Url", TEST_URL, result.get(0)[4]);
		Assert.assertEquals("Image url", TEST_IMG_URL, result.get(0)[5]);
		Assert.assertEquals("Video url", TEST_VIDEO_URL, result.get(0)[6]);
		Assert.assertEquals("Date", TEST_DATE_DEFAULT, result.get(0)[7].toString());
	}

	@Test
	@Transactional
	public void itShouldReturnDifferentRucsok_When_DownvotedTwice() {
		// Given

		createAVote(VoteTypeEntity.DOWN, 5, 1);
		createAVote(VoteTypeEntity.DOWN, 5, 2);

		// When

		List<Object[]> result = listedRucsokRepository.getHotRucsok(START_DATE, END_DATE, TEST_LIMIT);

		// Then

		Assert.assertEquals("Size", 1, result.size());
		Assert.assertNotEquals("Id", TEST_ID, result.get(0)[2]);
	}

	private void createAVote(VoteTypeEntity votetype, int rucsokId, int userId) {
		VoteEntity vote = new VoteEntity();
		VotePK key = new VotePK();
		key.setRucsokId(rucsokId);
		key.setUserId(userId);
		vote.setKey(key);
		vote.setVoteType(votetype);
		voteRepository.save(vote);
	}
}
