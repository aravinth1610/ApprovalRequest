package com.signin.request.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.signin.request.constant.AppConstant;
import com.signin.request.custome.annotation.ValidGmail;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GmailValidator implements ConstraintValidator<ValidGmail, String> {

	@Override
	public void initialize(ValidGmail constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String gmail, ConstraintValidatorContext context) {
		Pattern pattern = Pattern.compile(AppConstant.EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(gmail);
		return matcher.matches() && (gmail.length() >= 6 && gmail.length() <= 74);
	}

}
