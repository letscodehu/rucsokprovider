package com.rucsok.comment.convert;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

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
@ContextConfiguration(classes = { CommentEntityTransformerConfig.class , CommentEntityConverter.class })
public class CommentEntityConverterTest {
	
	private static final String TEST_STRING = "test";

	@Autowired
	private CommentEntityConverter underTest;

	@Autowired
	private DateService dateService;

	@Mock
	private Comment comment;

	@Mock
	private CommentEntity commentEntity;

	@Test
	public void convertShouldReturnEntityWithCorrectAttributes() {
		// Given
		Comment comment = Mockito.mock(Comment.class);
		Date date = Mockito.mock(Date.class);

		// When
		when(dateService.getCurrentTimestamp()).thenReturn(date);
		when(comment.getText()).thenReturn(TEST_STRING);

		CommentEntity result = underTest.convert(comment);
		// Then
		Assert.assertEquals("Text should match", TEST_STRING, result.getText());
		Assert.assertEquals("Date should match", date, result.getCreatedAt());
		Assert.assertNull("Parent should be null", result.getParent());
		Assert.assertNull("User should be null", result.getUser());
		Assert.assertNull("Rucsok should be null", result.getRucsok());
		
		verify(comment).getText();
		verify(dateService).getCurrentTimestamp();
	}
}
