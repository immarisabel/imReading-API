/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.googleSearchApi;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Tag(name = "Search Results Model")
public class SearchBooksDto {

 String title;
 String author;
 String isbn;
 String thumbnailUrl;
 int pages;
 String selfLink;
 String[] shelves;
}


