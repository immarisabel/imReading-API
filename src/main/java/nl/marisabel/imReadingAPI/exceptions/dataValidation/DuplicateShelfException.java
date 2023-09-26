/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.dataValidation;

public class DuplicateShelfException extends RuntimeException {
 public DuplicateShelfException(String shelfName) {
  super("A shelf with the same name already exists: " + shelfName);
 }

}
