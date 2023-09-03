/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.tags;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.imReadingAPI.domains.books.BooksEntity;
import nl.marisabel.imReadingAPI.domains.logs.LogsEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Table(name = "shelves")
public class TagsEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 @Getter
 @ManyToMany
 @JoinTable(
         name = "tags_logs",
         joinColumns = @JoinColumn(name = "log_id"),
         inverseJoinColumns = @JoinColumn(name = "tag_id"))
 @ToString.Exclude
 private List<LogsEntity> logs;

 public void setId(Long id) {
  this.id = id;
 }
}

