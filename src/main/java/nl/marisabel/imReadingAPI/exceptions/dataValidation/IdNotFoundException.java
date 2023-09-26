/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.dataValidation;

public class IdNotFoundException extends RuntimeException {
 public IdNotFoundException(Long id) {
  super("ID: " + id + " not found.");
 }
}

