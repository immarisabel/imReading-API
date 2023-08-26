package nl.marisabel.imReadingAPI.books;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.marisabel.imReadingAPI.shelves.ShelvesEntity;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class BooksEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "isbn", nullable = false)
 private String isbn;
 @NotNull
 private String title;
 @NotNull
 private String author;
 @NotNull
 private int pages;
 private String thumbnailUrl;

 // SHELVES
 @ManyToMany(mappedBy = "books")
 private List<ShelvesEntity> shelf;

}
