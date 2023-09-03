/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

public class IdNotFoundException extends RuntimeException {
 public IdNotFoundException(Long id) {
  super("Shelf not found with the provided ID: " + id);
 }
}

