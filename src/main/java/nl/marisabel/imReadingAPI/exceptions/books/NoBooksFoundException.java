/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.books;

import nl.marisabel.imReadingAPI.domains.books.BooksDTO;
import nl.marisabel.imReadingAPI.domains.books.BooksEntity;

import java.util.List;

public class NoBooksFoundException extends RuntimeException {
 public NoBooksFoundException(List<BooksDTO> books) {
  super("No books found in database.");
 }

}
