package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.configuration;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordPatternValidator implements ConstraintValidator<PasswordPattern, String> {

  private final PasswordPatternConfiguration passwordPatternConfiguration;

  @Override
  public void initialize(PasswordPattern constraintAnnotation) {}

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return false;
    return value.matches(passwordPatternConfiguration.getPasswordPattern());
  }
}
