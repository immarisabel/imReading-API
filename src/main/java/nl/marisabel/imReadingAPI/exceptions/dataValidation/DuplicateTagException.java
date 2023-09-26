/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.dataValidation;

public class DuplicateTagException extends RuntimeException {
 public DuplicateTagException(String tagName) {
  super("A tag with the same name already exists: " + tagName);
 }

}
