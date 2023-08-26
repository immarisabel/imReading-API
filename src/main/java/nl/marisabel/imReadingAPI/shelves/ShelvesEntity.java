package nl.marisabel.imReadingAPI.shelves;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import nl.marisabel.imReadingAPI.books.BooksEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shelves")
public class ShelvesEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 @NotNull
 private String name;
 @ManyToMany
 @JoinTable(
         name = "shelved_books",
         joinColumns = @JoinColumn(name = "shelves_shelf_id"),
         inverseJoinColumns = @JoinColumn(name = "books_isbn"))
 private List<BooksEntity> books;
}

