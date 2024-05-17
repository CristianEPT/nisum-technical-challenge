package com.nisum.core.nisumtechnicalchallenge.adapter.out.security;

import com.nisum.core.nisumtechnicalchallenge.application.port.out.TokenPort;
import org.springframework.stereotype.Component;

@Component
public class TokenAdapter implements TokenPort {
  @Override
  public String generateToken(String subject, String userName) {
    return "";
  }
}
