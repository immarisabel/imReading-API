/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

 private final JwtIssuer jwtIssuer;

 @PostMapping("/login")
 public LoginResponseModel login(@RequestBody @Validated LoginRequestModel request) {
  var token = jwtIssuer.generateToken(1L, request.getUsername(), List.of("ROLE_USER"));
  return LoginResponseModel.builder()
          .token(token)
          .build();
 }

}
