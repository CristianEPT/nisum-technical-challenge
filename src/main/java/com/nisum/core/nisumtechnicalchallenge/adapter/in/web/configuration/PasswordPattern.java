package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.configuration;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PasswordPatternValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordPattern {
  String message() default "Invalid password pattern";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
