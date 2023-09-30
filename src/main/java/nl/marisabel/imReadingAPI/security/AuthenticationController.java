/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import lombok.RequiredArgsConstructor;
import nl.marisabel.imReadingAPI.domains.books.BooksDTO;
import nl.marisabel.imReadingAPI.security.jwt.JwtIssuer;
import nl.marisabel.imReadingAPI.security.model.LoginRequestModel;
import nl.marisabel.imReadingAPI.security.model.LoginResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

 @GetMapping("/test")
 public String testSecurity(@AuthenticationPrincipal UserPrincipal principal) {
  return principal.getId() + " " + principal.getUsername() + " is authenticated";
 }


}
