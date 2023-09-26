/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.shelves;

import nl.marisabel.imReadingAPI.exceptions.CustomResponses;
import nl.marisabel.imReadingAPI.exceptions.ShelfNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShelvesExceptionHandler {

 /* Global exceptions handler under code 80X for Shelves Service */


 @ExceptionHandler(ShelfNotFoundException.class)
 public ResponseEntity<CustomResponses> handleNoReadingDataFoundForIsbn(ShelfNotFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(801, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }


}
