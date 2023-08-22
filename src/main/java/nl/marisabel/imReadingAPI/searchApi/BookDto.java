/*
 *
 *  * imReading API
 *  * Copyright (c) 2023 Marisabel Munoz
 *  * This project is licensed under the terms of the MIT License.
 *  * For more information, please see the https://opensource.org/license/mit/.
 *
 *
 */

package nl.marisabel.imReadingAPI.searchApi;



import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDto {

 String title;
 String author;
 String isbn;
 String thumbnail;
 int pageCount;
 String selfLink;
}


