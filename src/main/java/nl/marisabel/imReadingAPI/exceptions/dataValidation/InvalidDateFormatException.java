/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.dataValidation;

import java.util.Date;

public class InvalidDateFormatException extends RuntimeException {
 public InvalidDateFormatException(Date date) {
  super("Invalid date format: " + date + " Please use: ");
 }

}
