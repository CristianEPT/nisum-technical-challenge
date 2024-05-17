package com.nisum.core.nisumtechnicalchallenge.application.port.in;

import com.nisum.core.nisumtechnicalchallenge.domain.User;

public interface CreateUserUseCase {

  User createUser(User user);
}
