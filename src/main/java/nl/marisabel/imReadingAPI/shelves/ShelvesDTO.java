/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.shelves;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marisabel.imReadingAPI.books.BooksDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Shelves Model")
public class ShelvesDTO {
 private Long id;
 private String name;
}