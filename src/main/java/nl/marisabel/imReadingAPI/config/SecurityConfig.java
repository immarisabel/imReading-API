/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.config;

import lombok.RequiredArgsConstructor;
import nl.marisabel.imReadingAPI.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

 private final JwtAuthenticationFilter jwtAuthenticationFilter;

 @Bean
 public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

  http
          .cors().disable()
          .csrf().disable()
          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
          .formLogin().disable()
          .securityMatcher("/**")
          .authorizeHttpRequests(registry -> registry
                  .requestMatchers("/").permitAll()
                  .requestMatchers("/auth/login").permitAll()
                  .requestMatchers("/swagger-ui/index.html", "/swagger-ui*/**", "/v3/api-docs/**", "/swagger-ui.html", "/domains/**").permitAll()
                  .anyRequest().authenticated());
  return http.build();
 }


}
