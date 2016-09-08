package com.rucsok.comment;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.user.repository.dao.UserRepository;
import com.rucsok.user.repository.domain.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class CommentRepositoryIntegrationTest {

	private static final int TEST_DATA_SIZE = 4;
	private static final int TEST_DATA_REPLIES = 2;

	@Autowired
	private CommentRepository underTest;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	@Transactional
	public void countShouldReturnCorrectNumberOfEntites() {
		
		// Given
		
		// When
		
		long count = underTest.count();
		
		// Then
		
		Assert.assertEquals(TEST_DATA_SIZE, count);
	}

	@Test
	@Transactional
	public void commentShouldHaveCorrectNumberOfChildrens() {
		
		// Given
		
		// When
		
		CommentEntity result = underTest.findOne(Long.valueOf(1));
		long numberOfReplies = result.getReplies()
				.size();
		
		// Then
		
		Assert.assertEquals(TEST_DATA_REPLIES, numberOfReplies);
	}

	@Test
	@Transactional
	public void saveCommentShouldPersist() {
		
		// Given
		
		RucsokEntity rucsok = rucsokRepository.findOne(Long.valueOf(1));
		UserEntity user = userRepository.findOne(Long.valueOf(2));
		CommentEntity comment = new CommentEntity();
		comment.setRucsok(rucsok);
		comment.setUser(user);
		comment.setText("tunak tunak tun");
		
		// When
		
		underTest.save(comment);
		long count = underTest.count();
		CommentEntity result = underTest.findOne(comment.getId());
		
		// Then
		
		Assert.assertEquals("Data size should increase", TEST_DATA_SIZE + 1, count);
		Assert.assertEquals("Persisted data should match", comment, result);
	}

}
