/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {

 public BooksDTO createBook(BooksDTO booksDTO);

 BooksDTO getBookByIsbn(String isbn);

 List<BooksDTO> getAllBooks();

 BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO);

 boolean deleteBook(String isbn);
}





