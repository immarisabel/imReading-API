package nl.marisabel.imReadingAPI.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

 BooksEntity createBook(BooksEntity book);

 BooksEntity getBookById(String isbn);

 List<BooksEntity> getAllBooks();

 BooksEntity updateBook(String isbn, BooksEntity updatedBook);

 void deleteBook(String isbn);
}





