package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.mapper;

import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.model.PhoneEntity;
import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.model.UserEntity;
import com.nisum.core.nisumtechnicalchallenge.domain.Phone;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

  public static UserEntity mapUserDomainToModel(User user) {
    return UserEntity.builder()
        .userIdentifier(user.getId())
        .name(user.getName())
        .email(user.getEmail())
        .password(user.getPassword())
        .created(user.getCreated())
        .modified(user.getModified())
        .lastLogin(user.getLastLogin())
        .token(user.getToken())
        .isActive(user.isActive())
        .build();
  }

  public static PhoneEntity mapPhoneDomainToEntity(UserEntity userEntity, Phone phone) {
    return PhoneEntity.builder()
        .number(phone.number())
        .cityCode(phone.cityCode())
        .countryCode(phone.countryCode())
        .user(userEntity)
        .build();
  }
}
