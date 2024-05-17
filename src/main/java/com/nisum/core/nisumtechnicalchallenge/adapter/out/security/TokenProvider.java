package com.nisum.core.nisumtechnicalchallenge.adapter.out.security;

public interface TokenProvider {

  String generate(String subject, String userName);
}
