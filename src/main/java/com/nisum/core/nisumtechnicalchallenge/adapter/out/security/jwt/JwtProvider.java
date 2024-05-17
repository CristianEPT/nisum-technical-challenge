package com.nisum.core.nisumtechnicalchallenge.adapter.out.security.jwt;

import com.nisum.core.nisumtechnicalchallenge.adapter.out.security.TokenProvider;
import io.jsonwebtoken.Jwts;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider implements TokenProvider {

  @Override
  public String generate(String subject, String userName) {
    log.trace("creating Jwt {}", subject);
    var currentDate = LocalDateTime.now();
    return Jwts.builder()
        .subject(subject)
        .claim("user", userName)
        .issuedAt(new Date())
        .expiration(convertLocalDateTimeToDate(currentDate.plusDays(2)))
        .signWith(getSigningKey())
        .compact();
  }

  private Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
  }

  private SecretKey getSigningKey() {
    return Jwts.SIG.HS256.key().build();
  }
}
