package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence;

import com.nisum.core.nisumtechnicalchallenge.application.port.out.PersistencePort;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAdapter implements PersistencePort {

  private final UserProvider userProvider;

  @Override
  public boolean existEmail(String email) {
    return userProvider.existEmail(email);
  }

  @Override
  public User saveUser(User user) {
    return userProvider.saveUser(user);
  }
}
