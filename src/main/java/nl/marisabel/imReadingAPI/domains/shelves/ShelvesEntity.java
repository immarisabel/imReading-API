/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.shelves;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import nl.marisabel.imReadingAPI.domains.books.BooksEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Table(name = "shelves")
public class ShelvesEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 @Getter
 @ManyToMany
 @JoinTable(
         name = "shelved_books",
         joinColumns = @JoinColumn(name = "shelves_shelf_id"),
         inverseJoinColumns = @JoinColumn(name = "books_isbn"))
 @ToString.Exclude
 private List<BooksEntity> books;

 public void setBooks(List<BooksEntity> books) {
  this.books = books;
 }

 public void setId(Long id) {
  this.id = id;
 }
}
