/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginRequestModel {
 private String username;
 private String password;
}
