/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;

import nl.marisabel.imReadingAPI.domains.logs.LogsDTO;
import nl.marisabel.imReadingAPI.domains.logs.LogsEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BooksService {

 public BooksDTO createBook(BooksDTO booksDTO);

 BooksDTO getBookByIsbn(String isbn);

 List<BooksDTO> getAllBooks();

 BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO);

 boolean deleteBook(String isbn);

 private BooksDTO entityToDto(BooksEntity entity) {
  return null;
 }

 private BooksEntity dtoToEntity(BooksDTO dto) {
  return null;
 }

 private List<BooksDTO> convertEntityListToDtoList(List<BooksEntity> entityList) {
  return null;
 }

}





