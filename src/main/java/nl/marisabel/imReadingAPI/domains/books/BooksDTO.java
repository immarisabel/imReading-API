/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataEntity;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Books Model")
public class BooksDTO {
 private String isbn;
 private String title;
 private String author;
 private int pages;
 private String thumbnailUrl;
 private String selfLink;
 private List<String> shelves;
}