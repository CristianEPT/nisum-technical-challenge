package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2;

import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.UserProvider;
import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.mapper.UserMapper;
import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.model.UserEntity;
import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.repository.PhoneRepository;
import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.repository.UserRepository;
import com.nisum.core.nisumtechnicalchallenge.domain.Phone;
import com.nisum.core.nisumtechnicalchallenge.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class H2Provider implements UserProvider {

  private final UserRepository userRepository;
  private final PhoneRepository phoneRepository;

  @Override
  public boolean existEmail(String email) {
    log.trace("validating email {}", email);
    return userRepository.findAllByEmail(email).isPresent();
  }

  @Override
  @Transactional
  public User saveUser(User user) {
    log.trace("saving user {}", user.getEmail());
    var userModel = UserMapper.mapUserDomainToModel(user);
    var savedUser = userRepository.save(userModel);
    savePhones(savedUser, user.getPhones());
    return user;
  }

  private void savePhones(UserEntity savedUser, List<Phone> phones) {
    log.trace("saving phones {}", phones);
    phones.forEach(
        phone -> phoneRepository.save(UserMapper.mapPhoneDomainToEntity(savedUser, phone)));
  }
}
