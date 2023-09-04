/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;

import nl.marisabel.imReadingAPI.exceptions.CustomResponses;
import nl.marisabel.imReadingAPI.exceptions.InvalidDateException;
import nl.marisabel.imReadingAPI.exceptions.NoDataFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReadingDataExceptionHandler {

 /* Global exceptions handler under code 9XX for Reading Data Service */


 @ExceptionHandler(NoDataFoundException.class)
 public ResponseEntity<CustomResponses> handleNoReadingDataFoundForIsbn(NoDataFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(901, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(InvalidDateException.class)
 public ResponseEntity<CustomResponses> handleInvalidDateFormat(InvalidDateException ex) {
  CustomResponses errorResponse = new CustomResponses(603, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
 }

}
