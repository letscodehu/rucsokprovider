package com.rucsok.user.view.transform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.rucsok.user.domain.User;
import com.rucsok.user.domain.UserRegistration;
import com.rucsok.user.view.model.UserProfileView;
import com.rucsok.user.view.model.UserRegistrationError;
import com.rucsok.user.view.model.UserRegistrationRequest;
import com.rucsok.user.view.model.UserRegistrationResponse;

@Component
public class UserRegistrationTransformer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserRegistration transformToRegistration(UserRegistrationRequest request) {
		if (request == null) {
			throw new IllegalArgumentException();
		}
		return new UserRegistration(request.getEmail(), request.getUsername(), encodePassword(request));
	}

	private String encodePassword(UserRegistrationRequest request) {
		return passwordEncoder.encode(request.getPassword());
	}

	public UserRegistrationResponse transformToResponse(User user) {
		if (user == null) {
			throw new IllegalArgumentException();
		}
		UserProfileView userView = new UserProfileView(user.getUsername(), user.getEmail());
		return new UserRegistrationResponse(userView, null);
	}

	public UserRegistrationResponse transformToResponse(Exception exception) {
		return new UserRegistrationResponse(null, new UserRegistrationError(exception.getMessage()));
	}

	public UserRegistrationResponse transformToResponseError(List<ObjectError> allErrors) {
		StringBuilder sb = new StringBuilder();
		setErrorMessage(sb, allErrors.get(0));
		return new UserRegistrationResponse(null, new UserRegistrationError(sb.toString()));
	}

	private void setErrorMessage(StringBuilder sb, ObjectError error) {
		if (error instanceof FieldError) {
			sb.append(((FieldError) error).getField());
			sb.append(" ");
		}
		sb.append(error.getDefaultMessage());
	}

}
