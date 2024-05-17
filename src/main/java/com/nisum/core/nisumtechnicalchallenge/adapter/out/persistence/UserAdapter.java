package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence;

import com.nisum.core.nisumtechnicalchallenge.application.port.out.PersistencePort;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserAdapter implements PersistencePort {
  @Override
  public boolean existEmail(String email) {
    return false;
  }

  @Override
  public User saveUser(User user) {
    return null;
  }
}
