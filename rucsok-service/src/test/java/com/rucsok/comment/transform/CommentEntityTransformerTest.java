package com.rucsok.comment.transform;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentEntityTransformerConfig.class , CommentEntityTransformer.class })
public class CommentEntityTransformerTest {

	private static final String TEST_STRING = "test";

	@Autowired
	private CommentEntityTransformer underTest;

	@Autowired
	private DateService dateService;

	@Mock
	private Comment comment;

	@Mock
	private CommentEntity commentEntity;

	@Test
	public void itShouldReturnEntityWithCorrectAttributes() {
		// Given
		Comment comment = Mockito.mock(Comment.class);
		Date date = Mockito.mock(Date.class);

		// When
		when(dateService.getCurrentTimestamp()).thenReturn(date);
		when(comment.getText()).thenReturn(TEST_STRING);

		CommentEntity result = underTest.transformToEntity(comment);
		// Then
		Assert.assertEquals("Text should match", TEST_STRING, result.getText());
		Assert.assertEquals("Date should match", date, result.getCreatedAt());
		Assert.assertNull("Parent should be null", result.getParent());
		Assert.assertNull("User should be null", result.getUser());
		Assert.assertNull("Rucsok should be null", result.getRucsok());
		
		verify(comment).getText();
		verify(dateService).getCurrentTimestamp();
	}
	
	@Test
	public void itShouldReturnCommentWithCorrectAttributes() {
		// Given
		CommentEntity comment = Mockito.mock(CommentEntity.class);
		LocalDateTime timestamp = LocalDateTime.now(); // cannot be -simply- mocked
		Date date = Mockito.mock(Date.class);

		// When
		when(dateService.getLocaldateTimeFromDate(date)).thenReturn(timestamp);
		when(comment.getText()).thenReturn(TEST_STRING);
		when(comment.getCreatedAt()).thenReturn(date);

		Comment result = underTest.transformToComment(comment);
		// Then
		Assert.assertEquals("Text should match", TEST_STRING, result.getText());
		Assert.assertEquals("Date should match", timestamp, result.getCreatedAt());
		Assert.assertNull("Parent should be null", result.getParent());
		Assert.assertNull("User should be null", result.getUser());
		Assert.assertNull("Rucsok should be null", result.getRucsok());
		
		verify(comment).getText();
		verify(comment).getCreatedAt();
		verify(dateService).getLocaldateTimeFromDate(date);
	}
	
	
	@Test
	public void itShouldReturnCommentListWithCorrectAttributes() {
		// Given
		List<CommentEntity> commentList = new ArrayList<>();
		CommentEntity comment = Mockito.mock(CommentEntity.class);
		LocalDateTime timestamp = LocalDateTime.now(); // cannot be -simply- mocked
		Date date = Mockito.mock(Date.class);
		commentList.add(comment);

		// When
		when(dateService.getLocaldateTimeFromDate(date)).thenReturn(timestamp);
		when(comment.getText()).thenReturn(TEST_STRING);
		when(comment.getCreatedAt()).thenReturn(date);

		List<Comment> result = underTest.transformToCommentList(commentList);
		// Then
		Assert.assertEquals("Size should match", 1, result.size());
		Assert.assertEquals("Text should match", TEST_STRING, result.get(0).getText());
		Assert.assertEquals("Date should match", timestamp, result.get(0).getCreatedAt());
		Assert.assertNull("Parent should be null", result.get(0).getParent());
		Assert.assertNull("User should be null", result.get(0).getUser());
		Assert.assertNull("Rucsok should be null", result.get(0).getRucsok());
		
		verify(comment).getText();
		verify(comment).getCreatedAt();
		verify(dateService).getLocaldateTimeFromDate(date);
	}
}
