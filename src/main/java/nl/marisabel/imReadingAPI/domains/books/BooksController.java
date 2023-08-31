/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.CustomErrorResponse;
import nl.marisabel.imReadingAPI.customResponse.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/books"})
@Tag(name = "book service", description = "manage the books on the database")
public class BooksController {

 @Autowired
 private BooksServiceImplementation booksService;

 @ExceptionHandler(BookNotFoundException.class)
 public ResponseEntity<CustomErrorResponse> handleBookNotFoundException(BookNotFoundException ex) {
  CustomErrorResponse errorResponse = new CustomErrorResponse(601, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 /**
  * REQUEST BODY EXAMPLE
  {
  "isbn": "1234567890",
  "title": "Sample Book Title",
  "author": "Sample Author",
  "pages": 200,
  "thumbnailUrl": "thumbnail.jpg",
  }
  *
  * @param book
  * @return Response 200
  */
 @PostMapping
 public ResponseEntity<BooksDTO> createBook(@RequestBody BooksDTO book) {
  BooksDTO createdBook = booksService.createBook(book);
  log.info(String.valueOf(book));
  return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<?> getBookById(@PathVariable String isbn) {
  BooksDTO book = booksService.getBookByIsbn(isbn);

  if (book == null) {
   throw new BookNotFoundException(isbn);
  }

  return new ResponseEntity<>(book, HttpStatus.OK);
 }
 @GetMapping
 public List<BooksDTO> getAllBooks() {
  return booksService.getAllBooks();
 }

 @PutMapping("/{isbn}")
 public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BooksDTO updatedBook) {
  BooksDTO updated = booksService.updateBook(isbn, updatedBook);

  if (updated == null) {
   throw new BookNotFoundException(isbn);
  }

  return new ResponseEntity<>(updated, HttpStatus.OK);
 }


 @DeleteMapping("/{isbn}")
 public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
  try {
   boolean deleted = booksService.deleteBook(isbn);
   return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, deleted);
  } catch (Exception e) {
   throw new BookNotFoundException(isbn);
  }
 }


}
