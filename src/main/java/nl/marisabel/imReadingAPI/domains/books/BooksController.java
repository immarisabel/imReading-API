/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.customResponse.ResponseHandler;
import nl.marisabel.imReadingAPI.exceptions.books.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.books.NoBooksFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/books"})
@Tag(name = "2. book service", description = "manage the books on the database")
public class BooksController {

 @Autowired
 private BooksServiceImplementation booksService;


 @GetMapping
 public ResponseEntity<List<BooksDTO>> getAllBooks() {
  List<BooksDTO> books = booksService.getAllBooks();
  if (books.isEmpty()) {
   throw new NoBooksFoundException(books);
  } else {
   return ResponseEntity.ok(books);
  }
 }


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
