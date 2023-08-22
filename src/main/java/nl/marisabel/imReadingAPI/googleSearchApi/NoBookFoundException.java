/*
 *
 *  * imReading API
 *  * Copyright (c) 2023 Marisabel Munoz
 *  * This project is licensed under the terms of the MIT License.
 *  * For more information, please see the https://opensource.org/license/mit/.
 *
 *
 */

package nl.marisabel.imReadingAPI.googleSearchApi;

public class NoBookFoundException extends RuntimeException {
 public NoBookFoundException(String message) {
  super(message);
 }
}