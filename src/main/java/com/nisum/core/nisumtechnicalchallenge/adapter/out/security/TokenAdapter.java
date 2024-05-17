package com.nisum.core.nisumtechnicalchallenge.adapter.out.security;

import com.nisum.core.nisumtechnicalchallenge.application.port.out.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TokenAdapter implements TokenPort {

  private final TokenProvider tokenProvider;

  @Override
  public String generateToken(String subject, String userName) {
    return tokenProvider.generate(subject, userName);
  }
}
