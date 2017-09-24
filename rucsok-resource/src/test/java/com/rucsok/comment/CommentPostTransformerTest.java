package com.rucsok.comment;

import java.security.Principal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.config.CommentPostTransformerTestConfig;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.convert.CommentPostConverter;
import com.rucsok.comment.view.convert.CommentPostTransformer;
import com.rucsok.comment.view.model.CommentInsertRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentPostTransformerTestConfig.class, CommentPostTransformer.class })
public class CommentPostTransformerTest {

	private static final String TEST_USERNAME = "test";

	@Autowired
	private CommentPostTransformer underTest;

	@Mock
	private Principal principal;

	@Mock
	private CommentInsertRequest commentInsertRequest;

	@Spy
	private Comment comment;

	@Autowired
	private CommentPostConverter commentPostConverter;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itShouldConvertRequestToComment() {

		// Given
		// When

		Mockito.when(principal.getName()).thenReturn(TEST_USERNAME);
		Mockito.when(commentPostConverter.convert(commentInsertRequest)).thenReturn(comment);

		Comment result = underTest.transform(commentInsertRequest, principal);

		// Then

		Assert.assertEquals("Username should match", TEST_USERNAME, result.getUser().getUsername());
		Assert.assertEquals("Parent should be null", null, result.getParent());

		Mockito.verify(principal).getName();
		Mockito.verify(commentPostConverter).convert(commentInsertRequest);
	}

	@Test
	public void itShouldConvertRequestToComment_When_PrincipalNull() {

		// Given
		// When

		Mockito.when(commentPostConverter.convert(commentInsertRequest)).thenReturn(comment);

		Comment result = underTest.transform(commentInsertRequest, null);

		// Then

		Assert.assertEquals(null, result.getUser());
		Assert.assertEquals("Parent should be null", null, result.getParent());

		Mockito.verify(commentPostConverter).convert(commentInsertRequest);
	}
}
