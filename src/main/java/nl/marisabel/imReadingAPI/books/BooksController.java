package nl.marisabel.imReadingAPI.books;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"${url.mapping.v1}/books"})
public class BooksController {

 @Autowired
 private BooksServiceImplementation booksService;

 /**
  * REQUEST BODY EXAMPLE
  * {
  * "isbn": "1234567890",
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
 public BooksEntity createBook(@RequestBody BooksEntity book) {
  return booksService.createBook(book);
 }

 @GetMapping("/{isbn}")
 public BooksEntity getBookById(@PathVariable String isbn) {
  return booksService.getBookById(isbn);
 }

 @GetMapping
 public List<BooksEntity> getAllBooks() {
  return booksService.getAllBooks();
 }

 @PutMapping("/{isbn}")
 public BooksEntity updateBook(@PathVariable String isbn, @RequestBody BooksEntity updatedBook) {
  return booksService.updateBook(isbn, updatedBook);
 }

 @DeleteMapping("/{isbn}")
 public void deleteBook(@PathVariable String isbn) {
  booksService.deleteBook(isbn);
 }
}
