package com.rucsok.comment;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.view.transform.CommentTransformer;
import com.rucsok.config.RepositoryConfig;
import com.rucsok.config.TestConfig;
import com.rucsok.user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { RepositoryConfig.class, TestConfig.class })
@SpringBootTest
public class CommentServiceIntegrationTest {

	private static final String MOCK_TEXT = "mock text";
	private static final String USERNAME = "rucsok";

	private CommentService underTest;

	@Mock
	private Comment comment;

	@Mock
	private User user;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private CommentTransformer transformer;

	@Before
	public void setUp() {
		underTest = new CommentService();
		comment = Mockito.mock(Comment.class);
		user = Mockito.mock(User.class);
	}

	@Test
	public void test() {
		// Given
		// When
		when(comment.getText()).thenReturn(MOCK_TEXT);
		when(comment.getParent()).thenReturn(null);
		when(comment.getUser()).thenReturn(user);
		Comment result = underTest.saveRucsok(comment);
		// Then
		Assert.assertEquals(comment, result);
		verify(comment).getText();
		verify(comment).getParent();
		verify(comment).getUser();
	}
	
	private Comment findCommentByText(String text){
		Comment result = null;
//		commentRepo.findAll().forEach(c -> {
//			if(c.getText().equals(text)){
//				result = transformer.transformToComment(c);
//			}
//		});
		return result;
	}
	
}
