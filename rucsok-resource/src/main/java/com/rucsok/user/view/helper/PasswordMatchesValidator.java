package com.rucsok.user.view.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.rucsok.user.view.model.UserRegistrationRequest;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserRegistrationRequest> {

	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserRegistrationRequest request, ConstraintValidatorContext context) {
		return request.getPassword().equals(request.getPasswordConfirmation());
	}

}
