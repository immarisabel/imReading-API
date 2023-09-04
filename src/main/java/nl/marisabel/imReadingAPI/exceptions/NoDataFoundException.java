/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;


public class NoDataFoundException extends RuntimeException {
 public NoDataFoundException(String isbn) {
  super("No reading data found for ISBN: " + isbn);
 }

}
