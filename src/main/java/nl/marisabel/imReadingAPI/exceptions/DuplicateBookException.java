/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

public class DuplicateBookException extends RuntimeException {
 public DuplicateBookException(String isbn) {
  super("There is already a book with ISBN " + isbn + " in the database");
 }

}
