package com.rucsok.comment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.view.convert.CommentPostConverter;
import com.rucsok.comment.view.model.CommentInsertRequest;

import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentPostConverter.class })
public class CommentPostConverterTest {

	private static final String TEST_TEXT = "test";
	private static final int TEST_PARENT_ID = 2;
	private static final int TEST_RUCSOK_ID = 1;

	@Autowired
	private CommentPostConverter underTest;

	@Mock
	private CommentInsertRequest commentInsertRequest;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void convertShouldConvertRequestToComment() {

		// Given
		// When

		Mockito.when(commentInsertRequest.getText()).thenReturn(TEST_TEXT);
		Mockito.when(commentInsertRequest.getParentId()).thenReturn(TEST_PARENT_ID);
		Mockito.when(commentInsertRequest.getRucsokId()).thenReturn(TEST_RUCSOK_ID);

		Comment result = underTest.convert(commentInsertRequest);

		// Then

		Assert.assertEquals("Text should match", TEST_TEXT, result.getText());
		Assert.assertEquals("Parent id should match", TEST_PARENT_ID, result.getParent().getId());
		Assert.assertEquals("Rucsok id should match", TEST_RUCSOK_ID, result.getRucsok().getId());

		Mockito.verify(commentInsertRequest).getText();
		Mockito.verify(commentInsertRequest, times(2)).getParentId();
		Mockito.verify(commentInsertRequest).getRucsokId();
	}

	@Test
	public void convertShouldConvertRequestToComment_When_ParentIdIsZero() {

		// Given
		// When

		Mockito.when(commentInsertRequest.getText()).thenReturn(TEST_TEXT);
		Mockito.when(commentInsertRequest.getParentId()).thenReturn(0);
		Mockito.when(commentInsertRequest.getRucsokId()).thenReturn(TEST_RUCSOK_ID);

		Comment result = underTest.convert(commentInsertRequest);

		// Then

		Assert.assertEquals("Text should match", TEST_TEXT, result.getText());
		Assert.assertEquals("Parent should be null", null, result.getParent());
		Assert.assertEquals("Rucsok id should match", TEST_RUCSOK_ID, result.getRucsok().getId());

		Mockito.verify(commentInsertRequest).getText();
		Mockito.verify(commentInsertRequest).getParentId();
		Mockito.verify(commentInsertRequest).getRucsokId();
	}

	@Test
	public void convertShouldSetParentNull_When_ParentIdIsZero() {

		// Given
		// When

		Mockito.when(commentInsertRequest.getText()).thenReturn(TEST_TEXT);
		Mockito.when(commentInsertRequest.getParentId()).thenReturn(0);
		Mockito.when(commentInsertRequest.getRucsokId()).thenReturn(TEST_RUCSOK_ID);

		Comment result = underTest.convert(commentInsertRequest);

		// Then

		Assert.assertEquals("Text should match", TEST_TEXT, result.getText());
		Assert.assertEquals("Parent should be null", null, result.getParent());
		Assert.assertEquals("Rucsok id should match", TEST_RUCSOK_ID, result.getRucsok().getId());

		Mockito.verify(commentInsertRequest).getText();
		Mockito.verify(commentInsertRequest).getParentId();
		Mockito.verify(commentInsertRequest).getRucsokId();
	}
}
