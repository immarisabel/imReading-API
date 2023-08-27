/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.books;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.imReadingAPI.shelves.ShelvesEntity;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "books")
public class BooksEntity {
 @Id
 @Column(name = "isbn", nullable = false, unique = true)
 private String isbn;
 private String title;
 private String author;
 private int pages;
 private String thumbnailUrl;

 // SHELVES
 @ManyToMany(mappedBy = "books")
 @ToString.Exclude
 private List<ShelvesEntity> shelf;


 @Override
 public final boolean equals(Object o) {
  if (this == o) return true;
  if (o == null) return false;
  Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
  Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
  if (thisEffectiveClass != oEffectiveClass) return false;
  BooksEntity that = (BooksEntity) o;
  return getIsbn() != null && Objects.equals(getIsbn(), that.getIsbn());
 }

 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }
}
