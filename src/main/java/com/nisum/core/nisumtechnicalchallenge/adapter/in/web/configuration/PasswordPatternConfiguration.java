package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PasswordPatternConfiguration {

  @Value("${app.security.password.pattern}")
  private String passwordPattern;
}
