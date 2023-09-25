/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;


import nl.marisabel.imReadingAPI.exceptions.CustomResponses;
import nl.marisabel.imReadingAPI.exceptions.DuplicateBookException;
import nl.marisabel.imReadingAPI.exceptions.NoBooksFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BooksExceptionHandler {

 /* Global exceptions handler under code 70X for Books Service */

 @ExceptionHandler(DuplicateBookException.class)
 public ResponseEntity<CustomResponses> handleDuplicateBookException(DuplicateBookException ex) {
  CustomResponses errorResponse = new CustomResponses(703, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(NoBooksFoundException.class)
 public ResponseEntity<CustomResponses> handleEmptyListException(NoBooksFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(701, "There are no books in the database.");
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }
}
