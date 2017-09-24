package com.rucsok.comment;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rucsok.comment.convert.CommentConverter;
import com.rucsok.comment.convert.CommentEntityConverter;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.config.CommentServiceConfig;
import com.rucsok.rucsok.domain.Rucsok;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.user.domain.User;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.transform.UserTransformer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommentServiceConfig.class, CommentService.class, DateService.class })
@TestPropertySource("classpath:application.properties")
public class CommentServiceTest {

	private static final String TEST_TEXT = "test";

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Autowired
	private CommentService underTest;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Autowired
	private UserCheckerService userCheckerService;

	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	private CommentEntityConverter commentEntityConverter;

	@Autowired
	private CommentConverter commentConverter;

	@Mock
	private Comment comment;

	@Mock
	private Comment parent;

	@Mock
	private Rucsok rucsok;

	@Mock
	private User user;

	@Mock
	private CommentEntity commentEntity;
	
	@Mock
	private CommentEntity commentChildrenEntity;

	@Mock
	private CommentEntity commentParentEntity;

	@Mock
	private RucsokEntity existingRucsok;

	@Mock
	private UserEntity userEntity;

	private String testUsername;

	private long testId;

	private long testParentId;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void itShouldReturnCommentList_When_CalledWithExistingRucsokId() {
		// Given

		int rucsokId = 33;
		int offset = 1;

		@SuppressWarnings("unchecked")
		Page<CommentEntity> commenEntityPage = Mockito.mock(Page.class);
		@SuppressWarnings("unchecked")
		Page<Comment> commentPage = Mockito.mock(Page.class);
		PageRequest pageRequest = Mockito.mock(PageRequest.class);
		List<Comment> commentList = Arrays.asList(comment);

		// When

		when(commentRepository.findByRucsokIdAndParentNullOrderByCreatedAt(rucsokId, pageRequest))
				.thenReturn(commenEntityPage);
		when(commenEntityPage.map(commentConverter)).thenReturn(commentPage);
		when(commentPage.getContent()).thenReturn(commentList);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(userTransformer.transformEntityToUser(userEntity)).thenReturn(user);
		when(commentEntity.getUser()).thenReturn(userEntity);

		Page<Comment> result = underTest.findCommentsByRucsokId(rucsokId, pageRequest);

		// Then

		Assert.assertEquals("List should match", commentPage, result);
		Assert.assertEquals("List should match", offset, result.getContent().size());
		Assert.assertEquals("Username should match", testUsername, result.getContent().get(0).getUser().getUsername());

		verify(commentRepository).findByRucsokIdAndParentNullOrderByCreatedAt(rucsokId, pageRequest);
		verify(commenEntityPage).map(commentConverter);
		verify(comment).getUser();

	}
	
	@Test
	public void itShouldReturnCommentList_When_CalledWithExistingParentId() {
		// Given
		
		int parentId = 33;
		int offset = 1;
		
		@SuppressWarnings("unchecked")
		Page<CommentEntity> commentRepliesPage = Mockito.mock(Page.class);
		@SuppressWarnings("unchecked")
		Page<Comment> commentPage = Mockito.mock(Page.class);
		PageRequest pageRequest = Mockito.mock(PageRequest.class);
		List<Comment> commentList = Arrays.asList(comment);
		
		// When
		
		when(commentRepository.findByParentIdOrderByCreatedAt(parentId, pageRequest))
		.thenReturn(commentRepliesPage);
		when(commentRepliesPage.map(commentConverter)).thenReturn(commentPage);
		when(commentPage.getContent()).thenReturn(commentList);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(userTransformer.transformEntityToUser(userEntity)).thenReturn(user);
		when(commentEntity.getUser()).thenReturn(userEntity);
		
		Page<Comment> result = underTest.findCommentsByParentId(parentId, pageRequest);
		
		// Then
		
		Assert.assertEquals("List should match", commentPage, result);
		Assert.assertEquals("List should match", offset, result.getContent().size());
		Assert.assertEquals("Username should match", testUsername, result.getContent().get(0).getUser().getUsername());
		
		verify(commentRepository).findByParentIdOrderByCreatedAt(parentId, pageRequest);
		verify(commentRepliesPage).map(commentConverter);
		verify(comment).getUser();
		
	}
	
	@Test
	public void itShouldReturnEmptyCommentList_When_CalledWithNonExistingParentId() {
		// Given
		
		int parentId = 1000;
		int offset = 0;
		
		@SuppressWarnings("unchecked")
		Page<CommentEntity> commentRepliesPage = Mockito.mock(Page.class);
		@SuppressWarnings("unchecked")
		Page<Comment> commentPage = Mockito.mock(Page.class);
		PageRequest pageRequest = Mockito.mock(PageRequest.class);
		List<Comment> commentList =  Collections.emptyList();
		
		// When
		
		when(commentRepository.findByParentIdOrderByCreatedAt(parentId, pageRequest))
		.thenReturn(commentRepliesPage);
		when(commentRepliesPage.map(commentConverter)).thenReturn(commentPage);
		when(commentPage.getContent()).thenReturn(commentList);
		
		Page<Comment> result = underTest.findCommentsByParentId(parentId, pageRequest);
		
		// Then
		
		Assert.assertEquals("List size should match", offset, result.getContent().size());
		
		verify(commentRepository).findByParentIdOrderByCreatedAt(parentId, pageRequest);
		verify(commentRepliesPage).map(commentConverter);
		
	}

	@Test
	public void itShouldReturnEmptyCommentList_When_CalledWithNotExistingRucsokId() {
		// Given

		int rucsokId = 0;
		int offset = 0;

		@SuppressWarnings("unchecked")
		Page<CommentEntity> commenEntityPage = Mockito.mock(Page.class);
		@SuppressWarnings("unchecked")
		Page<Comment> commentPage = Mockito.mock(Page.class);
		PageRequest pageRequest = Mockito.mock(PageRequest.class);
		List<Comment> commentList = Collections.emptyList();

		// When

		when(commentRepository.findByRucsokIdAndParentNullOrderByCreatedAt(rucsokId, pageRequest))
				.thenReturn(commenEntityPage);
		when(commenEntityPage.map(commentConverter)).thenReturn(commentPage);
		when(commentPage.getContent()).thenReturn(commentList);

		Page<Comment> result = underTest.findCommentsByRucsokId(rucsokId, pageRequest);

		// Then

		Assert.assertEquals("Page should match", commentPage, result);
		Assert.assertEquals("List size should match", offset, result.getContent().size());

		verify(commentRepository).findByRucsokIdAndParentNullOrderByCreatedAt(rucsokId, pageRequest);
		verify(commenEntityPage).map(commentConverter);

	}

	@Test
	public void itShouldSaveNewComment_When_ParentIsSetAndParentIdIsNull() {
		// Given

		testId = 801L;
		testParentId = 482L;
		testUsername = "rucsoklikenotomorrow";
		long parentId = 0L;

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getText()).thenReturn(TEST_TEXT);
		when(parent.getId()).thenReturn(parentId);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
		when(commentRepository.findOne(parentId)).thenReturn(commentParentEntity);
		when(commentConverter.convert(commentEntity)).thenReturn(comment);

		underTest.saveRucsok(comment);

		// Then

		verify(commentEntity).setRucsok(existingRucsok);
		verify(comment).getRucsok();
		verify(comment, times(2)).getUser();
		verify(comment, times(2)).getParent();
		verify(user).getUsername();

		verify(commentEntityConverter).convert(comment);
		verify(rucsokRepository).findOne(testId);
		verify(userCheckerService, times(2)).findUserByName(testUsername);
		verify(commentConverter).convert(commentEntity);
	}

	@Test
	public void itShouldSaveNewComment_When_PostedToExistingRucsok() {
		// Given

		testId = 1L;
		testParentId = 2L;
		testUsername = "rucsok";

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getText()).thenReturn(TEST_TEXT);
		when(comment.getParent()).thenReturn(null);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(commentConverter.convert(commentEntity)).thenReturn(comment);
		when(commentRepository.save(commentEntity)).thenReturn(commentEntity);

		underTest.saveRucsok(comment);

		// Then

		verify(commentEntity).setRucsok(existingRucsok);
		verify(comment).getRucsok();
		verify(comment, times(2)).getUser();
		verify(comment).getParent();
		verify(user).getUsername();

		verify(commentEntityConverter).convert(comment);
		verify(rucsokRepository).findOne(testId);
		verify(userCheckerService).findUserByName(testUsername);
		verify(commentConverter).convert(commentEntity);
		verify(commentRepository).save(commentEntity);
	}

	@Test
	public void itShouldSaveNewComment_When_ParentIsSetAndParentExists() {
		// Given

		testId = 101L;
		testParentId = 282L;
		testUsername = "rucsoklikenotomorrow";

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getText()).thenReturn(TEST_TEXT);
		when(parent.getId()).thenReturn(testParentId);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(commentRepository.findOne(testParentId)).thenReturn(commentParentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(commentRepository.save(commentEntity)).thenReturn(commentEntity);
		when(commentConverter.convert(commentEntity)).thenReturn(comment);

		underTest.saveRucsok(comment);

		// Then

		verify(commentEntity).setRucsok(existingRucsok);
		verify(comment).getRucsok();
		verify(comment, times(2)).getUser();
		verify(comment, times(2)).getParent();
		verify(user).getUsername();

		verify(commentEntityConverter).convert(comment);
		verify(rucsokRepository).findOne(testId);
		verify(userCheckerService).findUserByName(testUsername);
		verify(commentConverter).convert(commentEntity);
	}

	@Test
	public void itShouldSaveNewComment_When_PostedToExistingComment() {
		// Given

		testId = 3L;
		testParentId = 4L;
		testUsername = "rucsok123";

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getText()).thenReturn(TEST_TEXT);
		when(parent.getId()).thenReturn(testParentId);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(commentRepository.findOne(testParentId)).thenReturn(commentParentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(commentConverter.convert(commentEntity)).thenReturn(comment);
		when(commentRepository.save(commentEntity)).thenReturn(commentEntity);

		underTest.saveRucsok(comment);

		// Then

		verify(commentEntity).setRucsok(existingRucsok);
		verify(commentEntity).setParent(commentParentEntity);
		verify(comment).getRucsok();
		verify(comment, times(2)).getUser();
		verify(comment, times(2)).getParent();
		verify(user).getUsername();

		verify(commentEntityConverter).convert(comment);
		verify(rucsokRepository).findOne(testId);
		verify(userCheckerService).findUserByName(testUsername);
		verify(commentConverter).convert(commentEntity);
		verify(commentRepository).save(commentEntity);
	}

	@Test
	public void itShouldThrowIllegalRucsokException_When_RucsokNotExists() {
		// Given

		testId = 10L;
		testParentId = 20L;
		testUsername = "rucsokbela";

		expectedException.expect(IllegalRucsokArgumentException.class);

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(null);

		underTest.saveRucsok(comment);

		// Then
	}

	@Test
	public void itShouldThrowIllegalRucsokException_When_ParentIsSetAndParentNotExists() {
		// Given

		testId = 111L;
		testParentId = 222L;
		testUsername = "rucsoklikenotomorrow";

		expectedException.expect(IllegalRucsokArgumentException.class);

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(parent);
		when(comment.getParent()).thenReturn(parent);
		when(parent.getId()).thenReturn(testParentId);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(commentRepository.findOne(testParentId)).thenReturn(null);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(userEntity);
		when(commentConverter.convert(commentEntity)).thenReturn(comment);

		underTest.saveRucsok(comment);

		// Then
	}

	@Test
	public void itShouldThrowIllegalRucsokException_When_TextIsNull() {
		// Given

		testId = 45L;
		testParentId = 98L;
		testUsername = "rucsoklikenotomorrow";

		expectedException.expect(IllegalRucsokArgumentException.class);
		expectedException.expectMessage("Cannot create comment without text!");

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getText()).thenReturn(null);

		underTest.saveRucsok(comment);

		// Then
	}

	@Test
	public void itShouldThrowIllegalRucsokException_When_TextIsEmpty() {
		// Given

		testId = 5454L;
		testParentId = 9845L;
		testUsername = "rucsoklikenotomorrow";

		expectedException.expect(IllegalRucsokArgumentException.class);
		expectedException.expectMessage("Cannot create comment without text!");

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getText()).thenReturn("");

		underTest.saveRucsok(comment);

		// Then
	}
	
	@Test
	public void itShouldThrowIllegalRucsokException_When_UserIsNullInComment() {
		// Given

		testId = 12L;
		testParentId = 42L;
		testUsername = "rucsokotto";

		expectedException.expect(IllegalRucsokArgumentException.class);
		expectedException.expectMessage("Cannot create comment without user!");
		
		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(null);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(null);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);

		underTest.saveRucsok(comment);

		// Then
	}

	@Test
	public void itShouldThrowIllegalRucsokException_When_UserNotExists() {
		// Given

		testId = 11L;
		testParentId = 22L;
		testUsername = "rucsokotto";

		expectedException.expect(IllegalRucsokArgumentException.class);

		// When

		when(comment.getRucsok()).thenReturn(rucsok);
		when(rucsok.getId()).thenReturn(testId);
		when(comment.getUser()).thenReturn(user);
		when(user.getUsername()).thenReturn(testUsername);
		when(comment.getParent()).thenReturn(null);

		when(commentEntityConverter.convert(comment)).thenReturn(commentEntity);
		when(rucsokRepository.findOne(testId)).thenReturn(existingRucsok);
		when(userCheckerService.findUserByName(testUsername)).thenReturn(null);

		underTest.saveRucsok(comment);

		// Then

	}

}