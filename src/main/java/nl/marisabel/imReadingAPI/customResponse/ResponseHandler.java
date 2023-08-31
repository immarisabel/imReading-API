/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.customResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
  Map<String, Object> map = new HashMap<>();
  map.put("message", message);
  map.put("status", status.value());
  map.put("data", responseObj);

  return new ResponseEntity<>(map, status);
 }
}