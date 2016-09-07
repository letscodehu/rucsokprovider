package com.rucsok.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rucsok.comment.domain.Comment;
import com.rucsok.comment.repository.dao.CommentRepository;
import com.rucsok.comment.repository.domain.CommentEntity;
import com.rucsok.comment.transform.CommentEntityTransformer;
import com.rucsok.rucsok.repository.dao.RucsokRepository;
import com.rucsok.rucsok.repository.domain.RucsokEntity;
import com.rucsok.rucsok.service.exception.IllegalRucsokArgumentException;
import com.rucsok.user.repository.domain.UserEntity;
import com.rucsok.user.service.UserCheckerService;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private RucsokRepository rucsokRepository;

	@Autowired
	private UserCheckerService userService;

	@Autowired
	private CommentEntityTransformer transformer;

	public Comment saveRucsok(Comment comment) {
		CommentEntity commentToSave = transformer.transformToEntity(comment);
		setRucsok(comment, commentToSave);
		setUser(comment, commentToSave);
		setParentIfExists(comment, commentToSave);
		return transformer.transformToComment(commentRepository.save(commentToSave));
	}

	private void setUser(Comment comment, CommentEntity commentToSave) {
		UserEntity user = findUserByName(comment);
		if (null == user) {
			throw new IllegalRucsokArgumentException();
		}
		commentToSave.setUser(user);
	}

	private UserEntity findUserByName(Comment comment) {
		return userService.findUserByName(comment.getUser()
				.getUsername());
	}

	private void setRucsok(Comment comment, CommentEntity commentToSave) {
		RucsokEntity rucsok = findRucsokById(comment);
		if (null == rucsok) {
			throw new IllegalRucsokArgumentException();
		}
		commentToSave.setRucsok(rucsok);
	}

	private void setParentIfExists(Comment comment, CommentEntity commentToSave) {
		if (null != comment.getParent()) {
			setParent(comment, commentToSave);
		}
	}

	private void setParent(Comment comment, CommentEntity commentToSave) {
		CommentEntity parent = findParentById(comment);
		if (null == parent) {
			throw new IllegalRucsokArgumentException();
		}
		commentToSave.setParent(parent);
	}

	private RucsokEntity findRucsokById(Comment comment) {
		return rucsokRepository.findOne(comment.getRucsok()
				.getId());
	}

	private CommentEntity findParentById(Comment comment) {
		return commentRepository.findOne(comment.getParent()
				.getId());
	}

}
