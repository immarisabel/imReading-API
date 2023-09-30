/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Getter
@Builder
@Component
public class UserPrincipal implements UserDetails {

 private final Long id;
 private final String username;
 private final String password;
 private final Collection<? extends GrantedAuthority> authorities;

 @Override
 public Collection<? extends GrantedAuthority> getAuthorities() {
  return authorities;
 }

 @Override
 public String getPassword() {
  return null;
 }

 @Override
 public String getUsername() {
  return username;
 }

 @Override
 public boolean isAccountNonExpired() {
  return true;
 }

 @Override
 public boolean isAccountNonLocked() {
  return true;
 }

 @Override
 public boolean isCredentialsNonExpired() {
  return true;
 }

 @Override
 public boolean isEnabled() {
  return true;
 }
}
