package com.nisum.core.nisumtechnicalchallenge.application.port.out;

public interface TokenPort {

  String generateToken(String subject, String userName);
}
