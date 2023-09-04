/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

public class InvalidDateException extends RuntimeException {
 public InvalidDateException() {
  super("Wrong date format, please use 'yyyy-MM-dd'T'HH:mm:ss.SSSX'.");
 }
}
