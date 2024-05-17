package com.nisum.core.nisumtechnicalchallenge.application.service;

import com.nisum.core.nisumtechnicalchallenge.application.port.in.CreateUserUseCase;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserService implements CreateUserUseCase {
  @Override
  public User createUser(User user) {
    return user;
  }
}
