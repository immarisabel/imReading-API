/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.marisabel.imReadingAPI.domains.books.BooksDTO;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Tag(name = "Reading Data Model")
public class ReadingDataDTO {
 private String bookIsbn;
 private Date startedDate;
 private Date finishedDate;
 private String status;
 private int currentPage;
 private int rating;
 private boolean favorite;
}