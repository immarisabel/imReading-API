package nl.marisabel.imReadingAPI.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {

 public BookDTO createBook(BookDTO bookDTO);

 BookDTO getBookByIsbn(String isbn);

 List<BookDTO> getAllBooks();

 BookDTO updateBook(String isbn, BookDTO updatedBookDTO);

 void deleteBook(String isbn);
}





