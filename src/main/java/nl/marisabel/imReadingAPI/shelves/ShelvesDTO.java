package nl.marisabel.imReadingAPI.shelves;

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
public class ShelvesDTO {
 private Long id;
 private String name;
}