package com.nisum.core.nisumtechnicalchallenge.adapter.in.web;

import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.mapper.UserWebMapper;
import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model.UserRequest;
import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model.UserResponse;
import com.nisum.core.nisumtechnicalchallenge.application.port.in.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {

  public static final String BASE_URL = "nisum/user";

  private final CreateUserUseCase createUserUseCase;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public UserResponse registerUser(@RequestBody @Valid UserRequest userRequest) {
    var user = UserWebMapper.mapUserModelToUserDomain(userRequest);
    var savedUser = createUserUseCase.createUser(user);
    return UserWebMapper.mapUserDomainToUserModel(savedUser);
  }
}
