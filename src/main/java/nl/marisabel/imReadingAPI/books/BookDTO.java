package nl.marisabel.imReadingAPI.books;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
 private String isbn;
 private String title;
 private String author;
 private int pages;
 private String thumbnailUrl;
}