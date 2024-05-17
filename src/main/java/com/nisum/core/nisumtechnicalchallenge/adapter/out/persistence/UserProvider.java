package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence;

import com.nisum.core.nisumtechnicalchallenge.domain.User;

public interface UserProvider {

  boolean existEmail(String email);

  User saveUser(User user);
}
