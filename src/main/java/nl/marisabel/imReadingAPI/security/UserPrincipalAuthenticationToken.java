/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalAuthenticationToken extends AbstractAuthenticationToken {

 private final UserPrincipal userPrincipal;

 public UserPrincipalAuthenticationToken(UserPrincipal userPrincipal) {
  super(userPrincipal.getAuthorities());
  this.userPrincipal = userPrincipal;
 }


 @Override
 public Object getCredentials() {
  return null;
 }

 @Override
 public UserPrincipal getPrincipal() {
  return userPrincipal;
 }
}
