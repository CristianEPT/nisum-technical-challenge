package com.nisum.core.nisumtechnicalchallenge.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import com.nisum.core.nisumtechnicalchallenge.application.port.out.PersistencePort;
import com.nisum.core.nisumtechnicalchallenge.application.port.out.TokenPort;
import com.nisum.core.nisumtechnicalchallenge.domain.Phone;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import com.nisum.core.nisumtechnicalchallenge.domain.exception.EmailAlreadyExistsException;
import com.nisum.core.nisumtechnicalchallenge.domain.exception.UserServiceException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @Mock PersistencePort persistencePort;

  @Mock TokenPort tokenPort;

  @InjectMocks UserService userService;

  private static final String TEST_TOKEN =
      "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjcmlzdGlhbkB0ZXN0LmNvbSIsInVzZXIiOiJDcmlzdGlhbiIsImlhdCI6MTcxNTk3MTUzOCwiZXhwIjoxNzE2MTQ0MzM3fQ.CxAkjtldTdgsWyQ6WCIcTkONrqiN8S_D_IP2ZDTug3I";

  @Test
  void givenCorrectUser_ThenSaveUser_ReturnInformation() {
    var correctUser =
        new User(
            "test", "test@domain.com", "Password123", List.of(new Phone("123123123", "1", "57")));

    given(persistencePort.existEmail("test@domain.com")).willReturn(false);
    given(tokenPort.generateToken(Mockito.any(), Mockito.any())).willReturn(TEST_TOKEN);
    given(persistencePort.saveUser(Mockito.any()))
        .willReturn(
            new User(
                "398b6992-81f5-41e1-bd75-b3f5808a5be6",
                "test",
                "test@domain.com",
                "Password123",
                1687684166103L,
                1687684166103L,
                1687684166103L,
                TEST_TOKEN,
                true,
                List.of(new Phone("123123123", "1", "57"))));

    var savedUser = userService.createUser(correctUser);
    assertThat(savedUser).isNotNull();
    assertThat(savedUser.getId()).isNotNull().isNotEmpty();
    assertThat(savedUser.getCreated()).isNotNull().isGreaterThan(0);
    assertThat(savedUser.getModified()).isNotNull().isGreaterThan(0);
    assertThat(savedUser.getLastLogin()).isNotNull().isGreaterThan(0);
    assertThat(savedUser.getToken()).isNotNull().isNotEmpty();
    assertThat(savedUser.isActive()).isTrue();
  }

  @Test
  void givenIncorrectUser_WhenEmailExist_ThenDontSaveUser_ThrowException() {
    var correctUser =
        new User(
            "test", "test@domain.com", "Password@123", List.of(new Phone("123123123", "1", "57")));
    given(persistencePort.existEmail("test@domain.com")).willReturn(true);
    assertThrows(EmailAlreadyExistsException.class, () -> userService.createUser(correctUser));
  }

  @Test
  void givenCorrectUser_FailDatabaseConnection_ThenDontSaveUser_ThrowException() {
    var correctUser =
        new User(
            "test", "test@domain.com", "Password@123", List.of(new Phone("123123123", "1", "57")));
    given(persistencePort.existEmail("test@domain.com"))
        .willThrow(new RuntimeException("Database connection error"));
    assertThrows(UserServiceException.class, () -> userService.createUser(correctUser));
  }

  @Test
  void givenCorrectUser_FailGeneratingToken_ThenDontSaveUser_ThrowException() {
    var correctUser =
        new User(
            "test", "test@domain.com", "Password@123", List.of(new Phone("123123123", "1", "57")));
    given(persistencePort.existEmail("test@domain.com")).willReturn(false);
    given(tokenPort.generateToken(Mockito.any(), Mockito.any()))
        .willThrow(new RuntimeException("error generating token"));
    assertThrows(UserServiceException.class, () -> userService.createUser(correctUser));
  }

  @Test
  void givenCorrectUser_FailSavingUser_ThenDontSaveUser_ThrowException() {
    var correctUser =
        new User(
            "test", "test@domain.com", "Password@123", List.of(new Phone("123123123", "1", "57")));
    given(persistencePort.existEmail("test@domain.com")).willReturn(false);
    given(tokenPort.generateToken(Mockito.any(), Mockito.any())).willReturn(TEST_TOKEN);
    given(persistencePort.saveUser(Mockito.any()))
        .willThrow(new RuntimeException("Database connection error"));
    assertThrows(UserServiceException.class, () -> userService.createUser(correctUser));
  }
}
