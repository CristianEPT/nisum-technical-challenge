package com.nisum.core.nisumtechnicalchallenge.application.service;

import com.nisum.core.nisumtechnicalchallenge.application.port.in.CreateUserUseCase;
import com.nisum.core.nisumtechnicalchallenge.application.port.out.PersistencePort;
import com.nisum.core.nisumtechnicalchallenge.application.port.out.TokenPort;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import com.nisum.core.nisumtechnicalchallenge.domain.exception.EmailAlreadyExistsException;
import com.nisum.core.nisumtechnicalchallenge.domain.exception.UserServiceException;
import java.time.Instant;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Setter
public class UserService implements CreateUserUseCase {

  private final PersistencePort persistencePort;
  private final TokenPort tokenPort;

  @Override
  public User createUser(@NonNull User user) {
    log.trace("register user {}", user);
    validateEmail(user);
    var userJwt = getToken(user);
    return saveUser(user, userJwt);
  }

  private void validateEmail(User user) {
    if (existEmail(user.getEmail()))
      throw new EmailAlreadyExistsException("The email " + user.getEmail() + " already exist");
  }

  private boolean existEmail(String email) {
    log.trace("validating if exist email {}", email);
    try {
      return persistencePort.existEmail(email);
    } catch (Exception e) {
      log.error("cannot validate if exist email {}", email, e);
      throw new UserServiceException("cannot validate email " + email);
    }
  }

  private String getToken(User user) {
    log.trace("generating token for user {}", user);
    try {
      return tokenPort.generateToken(user.getEmail(), user.getName());
    } catch (Exception e) {
      log.error("cannot generate token for user {}", user.getEmail(), e);
      throw new UserServiceException("cannot generate token for user " + user.getEmail());
    }
  }

  private User saveUser(User user, String userJwt) {
    try {
      var id = UUID.randomUUID().toString();
      var currentDate = Instant.now().toEpochMilli();
      var userToSave =
          new User(
              id,
              user.getName(),
              user.getEmail(),
              user.getPassword(),
              currentDate,
              currentDate,
              currentDate,
              userJwt,
              true,
              user.getPhones());
      log.trace("saving user {}", userToSave);
      return persistencePort.saveUser(userToSave);
    } catch (Exception e) {
      log.error("cannot save user {}", user, e);
      throw new UserServiceException("cannot save user " + user.getEmail());
    }
  }
}
