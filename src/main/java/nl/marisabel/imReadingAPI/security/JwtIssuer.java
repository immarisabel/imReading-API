/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtIssuer {

 private final JwtProperties jwtProperties;

 public String generateToken(Long id, String username, List<String> roles) {
  return JWT.create()
          .withSubject(id.toString())
          .withExpiresAt(Instant.now().plus(1, java.time.temporal.ChronoUnit.DAYS))
          .withClaim("username", username)
          .withClaim("roles", roles)
          .sign(Algorithm.HMAC256(jwtProperties.getKey()));
 }

}