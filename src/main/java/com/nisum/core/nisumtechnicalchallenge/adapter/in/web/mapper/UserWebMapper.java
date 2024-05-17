package com.nisum.core.nisumtechnicalchallenge.adapter.in.web.mapper;

import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model.PhoneRequest;
import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model.UserRequest;
import com.nisum.core.nisumtechnicalchallenge.adapter.in.web.model.UserResponse;
import com.nisum.core.nisumtechnicalchallenge.domain.Phone;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserWebMapper {

  public static User mapUserModelToUserDomain(UserRequest userRequest) {
    var phones =
        userRequest.phones().stream().map(UserWebMapper::mapPhoneModelToPhoneDomain).toList();
    return new User(userRequest.name(), userRequest.email(), userRequest.password(), phones);
  }

  private static Phone mapPhoneModelToPhoneDomain(PhoneRequest phoneRequest) {
    return new Phone(phoneRequest.number(), phoneRequest.cityCode(), phoneRequest.countryCode());
  }

  public static UserResponse mapUserDomainToUserModel(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .created(user.getCreated())
        .modified(user.getModified())
        .lastLogin(user.getLastLogin())
        .token(user.getToken())
        .isActive(user.isActive())
        .build();
  }
}
