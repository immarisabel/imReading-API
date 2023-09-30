/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nl.marisabel.imReadingAPI.security.UserPrincipalAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

 private final JwtDecoder jwtDecoder;

 private final JwtToPrincipalConverter jwtToPrincipalConverter;

 @Override
 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
  extractTokenFromRequest(request)
          .map(jwtDecoder::decode)
          .map(jwtToPrincipalConverter::convert)
          .map(UserPrincipalAuthenticationToken::new)
          .ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
  filterChain.doFilter(request, response);
 }

 private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
  var authorizationHeader = request.getHeader("Authorization");
  if (StringUtils.containsAnyIgnoreCase(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
   return Optional.of(authorizationHeader.substring(7));
  }
  return Optional.empty();
 }
}
