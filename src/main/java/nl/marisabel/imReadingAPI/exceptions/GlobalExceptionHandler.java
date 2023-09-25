/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

 /* Global exceptions handler under code 6XX */
 @ExceptionHandler(BookNotFoundException.class)
 public ResponseEntity<CustomResponses> handleBookNotFoundException(BookNotFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(601, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(IdNotFoundException.class)
 public ResponseEntity<CustomResponses> handleIDNotFoundException(IdNotFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(602, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }


 @ExceptionHandler(NothingFoundWithIsbnException.class)
 public ResponseEntity<CustomResponses> handleNoReadingDataFoundForIsbn(NothingFoundWithIsbnException ex) {
  CustomResponses errorResponse = new CustomResponses(603, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 /* Global exceptions handler under code 5XX */

 @ExceptionHandler(Exception.class)
 public ResponseEntity<CustomResponses> handleGenericException(Exception ex) {
  CustomResponses errorResponse = new CustomResponses(500, "Internal Server Error");
  return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
 }
}
