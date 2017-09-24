package com.rucsok.comment.convert;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.DateService;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.config.CommentEntityTransformerConfig;
import com.rucsok.user.domain.User;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.transform.UserTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentEntityTransformerConfig.class, CommentConverter.class })
public class CommentConverterTest {

	private static final String TEST_STRING = "test";

	@Autowired
	private CommentConverter underTest;

	@Autowired
	private DateService dateService;

	@Autowired
	private UserTransformer userTransformer;

	@Mock
	private Comment comment;

	@Mock
	private User user;

	@Mock
	private CommentEntity commentEntity;

	@Mock
	private UserEntity userEntity;

	@Test
	public void itShouldReturnCommentWithCorrectAttributes() {
		// Given
		CommentEntity commentEntity = Mockito.mock(CommentEntity.class);
		LocalDateTime timestamp = LocalDateTime.now(); // cannot -simply- mocked
		Date date = Mockito.mock(Date.class);

		// When
		when(dateService.getLocaldateTimeFromDate(date)).thenReturn(timestamp);
		when(commentEntity.getText()).thenReturn(TEST_STRING);
		when(commentEntity.getCreatedAt()).thenReturn(date);
		when(commentEntity.getUser()).thenReturn(userEntity);
		when(userTransformer.transformEntityToUser(userEntity)).thenReturn(user);

		Comment result = underTest.convert(commentEntity);
		// Then
		Assert.assertEquals("Text should match", TEST_STRING, result.getText());
		Assert.assertEquals("Date should match", timestamp, result.getCreatedAt());
		Assert.assertNull("Parent should be null", result.getParent());
		Assert.assertEquals("User should match", user, result.getUser());
		Assert.assertNull("Rucsok should be null", result.getRucsok());

		verify(commentEntity).getText();
		verify(commentEntity).getCreatedAt();
		verify(dateService).getLocaldateTimeFromDate(date);
	}

}
