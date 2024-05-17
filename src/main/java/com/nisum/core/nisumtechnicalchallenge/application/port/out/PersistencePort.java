package com.nisum.core.nisumtechnicalchallenge.application.port.out;

import com.nisum.core.nisumtechnicalchallenge.domain.User;

public interface PersistencePort {
  boolean existEmail(String email);

  User saveUser(User user);
}
