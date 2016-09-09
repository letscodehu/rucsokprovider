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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  CommentPostConverter.class })
public class CommentPostConverterTest {

	private static final String TEST_TEXT = "test";
	private static final int TEST_PARENT_ID = 0;
	private static final int TEST_RUCSOK_ID = 1;

	@Autowired
	private CommentPostConverter underTest;
	
	@Mock
	private CommentInsertRequest commentInsertRequest;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itShouldConvertRequestToComment() {
		
		// Given
		// When

		Mockito.when(commentInsertRequest.getText()).thenReturn(TEST_TEXT);
		Mockito.when(commentInsertRequest.getParentId()).thenReturn(TEST_PARENT_ID);
		Mockito.when(commentInsertRequest.getRucsokId()).thenReturn(TEST_RUCSOK_ID);
		
		Comment result = underTest.convert(commentInsertRequest);

		// Then

		Assert.assertEquals("Text should match", TEST_TEXT, result.getText());
		Assert.assertEquals("Date should match", TEST_PARENT_ID, result.getParent().getId());
		Assert.assertEquals("Username should match", TEST_RUCSOK_ID, result.getRucsok().getId());
		
		Mockito.verify(commentInsertRequest).getText();
		Mockito.verify(commentInsertRequest).getParentId();
		Mockito.verify(commentInsertRequest).getRucsokId();
	}
}
