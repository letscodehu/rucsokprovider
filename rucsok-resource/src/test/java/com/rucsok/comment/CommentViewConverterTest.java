package com.rucsok.comment;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.config.CommentViewConverterConfig;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.transform.DateTimeTransformer;
import com.rucsok.comment.transform.OutputTextTransformer;
import com.rucsok.comment.view.convert.CommentViewConverter;
import com.rucsok.comment.view.model.CommentView;
import com.rucsok.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentViewConverterConfig.class, CommentViewConverter.class })
public class CommentViewConverterTest {

	private static final String TEST_USERNAME = "rucsok";
	private static final String TEST_DATE = "yyyy.MM.dd HH:mm:ss";
	private static final String TEST = "test";

	@Autowired
	private CommentViewConverter underTest;

	@Autowired
	private DateTimeTransformer formatter;

	@Autowired
	private OutputTextTransformer outputTextTransformer;

	@Test
	public void itShouldReturnCommentView() {
		// Given

		LocalDateTime timestamp = LocalDateTime.now(); // cannot simply mocked
		Comment comment = Mockito.mock(Comment.class);
		User user = Mockito.mock(User.class);

		// When

		when(outputTextTransformer.escape(TEST)).thenReturn(TEST);
		when(formatter.format(timestamp)).thenReturn(TEST_DATE);
		when(comment.getText()).thenReturn(TEST);
		when(comment.getCreatedAt()).thenReturn(timestamp);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(TEST_USERNAME);

		CommentView result = underTest.convert(comment);

		// Then

		Assert.assertEquals("Text should match", TEST, result.getText());
		Assert.assertEquals("Date should match", TEST_DATE, result.getDate());
		Assert.assertEquals("Username should match", TEST_USERNAME, result.getUsername());
	}

}
