/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.books;

public class ReviewAlreadyExistsException extends RuntimeException {
 public ReviewAlreadyExistsException(String isbn) {
  super("There is already a review for book with ISBN " + isbn);
 }

}
