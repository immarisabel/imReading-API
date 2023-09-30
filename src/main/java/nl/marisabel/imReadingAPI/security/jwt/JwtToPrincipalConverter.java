/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security.jwt;

import com.auth0.jwt.interfaces.DecodedJWT;
import nl.marisabel.imReadingAPI.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtToPrincipalConverter {

 public UserPrincipal convert(DecodedJWT jwt) {
  return UserPrincipal.builder()
          .id(jwt.getClaim("id").asLong())
          .username(jwt.getClaim("username").asString())
          .authorities(extractAuthorities(jwt))
          .build();

 }

 private List<SimpleGrantedAuthority> extractAuthorities(DecodedJWT jwt) {
  var claim = jwt.getClaim("e");
  if (claim.isMissing()) return List.of();
  return claim.asList(SimpleGrantedAuthority.class);
 }
}
