package com.signin.request.custome.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.signin.request.validations.RepartPasswordEqualValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE  })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RepartPasswordEqualValidator.class)
public @interface RepartPasswordEqual {

	String message() default "Password mismatch";
    String passwordFieldFirst();
    String passwordFieldSecond();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
