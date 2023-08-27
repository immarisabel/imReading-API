package nl.marisabel.imReadingAPI.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {

 public BooksDTO createBook(BooksDTO booksDTO);

 BooksDTO getBookByIsbn(String isbn);

 List<BooksDTO> getAllBooks();

 BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO);

 void deleteBook(String isbn);
}





