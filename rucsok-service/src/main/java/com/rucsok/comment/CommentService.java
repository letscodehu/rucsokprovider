package com.rucsok.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.rucsok.comment.convert.CommentConverter;
import com.rucsok.comment.convert.CommentEntityConverter;
import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserCheckerService;
import com.rucsok.user.transform.UserTransformer;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Autowired
	private UserCheckerService userService;

	@Autowired
	private CommentConverter commentConverter;

	@Autowired
	private CommentEntityConverter commentEntityConverter;

	@Autowired
	private UserTransformer userTransformer;

	public Page<Comment> findCommentsByParentId(int parentId, PageRequest pageRequest) {
		return commentRepository.findByParentIdOrderByCreatedAt(parentId, pageRequest).map(commentConverter);
	}

	public Page<Comment> findCommentsByRucsokId(int rucsokId, PageRequest pageRequest) {
		return commentRepository.findByRucsokIdAndParentNullOrderByCreatedAt(rucsokId, pageRequest)
				.map(commentConverter);
	}

	public Comment saveRucsok(Comment comment) {
		checkIfUserExists(comment);
		checkIfTextIsNull(comment);
		CommentEntity commentToSave = commentEntityConverter.convert(comment);
		setRucsok(comment, commentToSave);
		setUserToEntity(comment, commentToSave);
		setParentIfExists(comment, commentToSave);
		return commentConverter.convert(commentRepository.save(commentToSave));
	}

	private void checkIfTextIsNull(Comment comment) {
		if (null == comment.getText() || 0 == comment.getText().length()) {
			throw new IllegalRucsokArgumentException("Cannot create comment without text!");
		}
	}

	private void checkIfUserExists(Comment comment) {
		if (null == comment.getUser()) {
			throw new IllegalRucsokArgumentException("Cannot create comment without user!");
		}
	}

	private void setUserToEntity(Comment comment, CommentEntity commentToSave) {
		UserEntity user = findUserByName(comment);
		assertNotNull(user, "User is null!");
		commentToSave.setUser(user);
	}

	private UserEntity findUserByName(Comment comment) {
		return userService.findUserByName(comment.getUser().getUsername());
	}

	private void setRucsok(Comment comment, CommentEntity commentToSave) {
		RucsokEntity rucsok = findRucsokById(comment);
		assertNotNull(rucsok, "Rucsok is null!");
		commentToSave.setRucsok(rucsok);
	}

	private void setParentIfExists(Comment comment, CommentEntity commentToSave) {
		if (null != comment.getParent()) {
			setParent(comment, commentToSave);
		}
	}

	private void setParent(Comment comment, CommentEntity commentToSave) {
		CommentEntity parent = findParentById(comment);
		assertNotNull(parent, "Parent is null!");
		commentToSave.setParent(parent);
	}

	private void assertNotNull(Object object, String errorMessage) {
		if (null == object) {
			throw new IllegalRucsokArgumentException(errorMessage);
		}
	}

	private RucsokEntity findRucsokById(Comment comment) {
		return rucsokRepository.findOne(comment.getRucsok().getId());
	}

	private CommentEntity findParentById(Comment comment) {
		return commentRepository.findOne(comment.getParent().getId());
	}

}
