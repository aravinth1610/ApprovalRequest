package com.signin.request.validations;

import com.signin.request.custome.annotation.RepartPasswordEqual;
import com.signin.request.payload.request.AuthsignUpRequest;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RepartPasswordEqualValidator implements ConstraintValidator<RepartPasswordEqual, AuthsignUpRequest> {
	private String passwordFieldSecond;
	private String message;

	@Override
	public void initialize(RepartPasswordEqual constraintAnnotation) {
		passwordFieldSecond = constraintAnnotation.passwordFieldSecond();
		message = constraintAnnotation.message();

	}

	@Override
	public boolean isValid(AuthsignUpRequest signUpRequest, ConstraintValidatorContext context) {
		boolean valid = true;
		valid = isPasswordConfirmPasswordMatched(signUpRequest);
		if (!valid) {
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(passwordFieldSecond)
					.addConstraintViolation().disableDefaultConstraintViolation();
		}
		System.out.println(valid);
		return valid;
	}

	private Boolean isPasswordConfirmPasswordMatched(AuthsignUpRequest signUpRequest) {
		return (signUpRequest.getPassword() != null && signUpRequest.getConfirmPassword() != null
				&& signUpRequest.getPassword().equals(signUpRequest.getConfirmPassword()));
	}

}
