package nl.marisabel.imReadingAPI.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServiceImplementation implements BookService {

 public BooksServiceImplementation(BooksRepository booksRepository) {
  this.booksRepository = booksRepository;
 }

 private final BooksRepository booksRepository;

 @Override
 public BooksEntity createBook(BooksEntity book) {
  return booksRepository.save(book);
 }

 @Override
 public BooksEntity getBookById(String isbn) {
  return booksRepository.findById(isbn).orElse(null);
 }

 @Override
 public List<BooksEntity> getAllBooks() {
  return (List<BooksEntity>) booksRepository.findAll();
 }

 @Override
 public BooksEntity updateBook(String isbn, BooksEntity updatedBook) {
  if (booksRepository.existsById(isbn)) {
   updatedBook.setId(isbn);
   return booksRepository.save(updatedBook);
  }
  return null;
 }

 @Override
 public void deleteBook(String isbn) {
  booksRepository.deleteById(isbn);
 }
}


