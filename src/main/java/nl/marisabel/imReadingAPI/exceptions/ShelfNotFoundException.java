/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

public class ShelfNotFoundException extends RuntimeException {
 public ShelfNotFoundException(Long id) {
  super("Shelf not found with the provided ID: " + id);
 }
}

