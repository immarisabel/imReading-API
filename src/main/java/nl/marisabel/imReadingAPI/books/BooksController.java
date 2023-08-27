package nl.marisabel.imReadingAPI.books;

import lombok.extern.java.Log;
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
public class BooksController {

 @Autowired
 private BooksServiceImplementation booksService;

 /**
  * REQUEST BODY EXAMPLE
  * {
  * "id": "1234567890",
  * "title": "Sample Book Title",
  * "author": "Sample Author",
  * "pages": 200,
  * "thumbnailUrl": "https://example.com/sample-thumbnail.jpg"
  * }
  *
  * @param book
  * @return Response 200
  */
 @PostMapping
 public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO book) {
  BookDTO createdBook = booksService.createBook(book);
  log.info(String.valueOf(book));
  return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
 }

 @GetMapping("/{isbn}")
 public BookDTO getBookById(@PathVariable String isbn) {
  return booksService.getBookByIsbn(isbn);
 }

 @GetMapping
 public List<BookDTO> getAllBooks() {
  return booksService.getAllBooks();
 }

 @PutMapping("/{isbn}")
 public BookDTO updateBook(@PathVariable String isbn, @RequestBody BookDTO updatedBook) {
  return booksService.updateBook(isbn, updatedBook);
 }

 @DeleteMapping("/{isbn}")
 public void deleteBook(@PathVariable String isbn) {
  booksService.deleteBook(isbn);
 }
}
