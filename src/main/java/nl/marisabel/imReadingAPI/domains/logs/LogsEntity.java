/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import jakarta.persistence.*;
import lombok.*;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesEntity;
import nl.marisabel.imReadingAPI.domains.tags.TagsEntity;
import org.hibernate.proxy.HibernateProxy;

import java.sql.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "logs")
public class LogsEntity {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String bookIsbn;
 private Date date;
 private int page;
 private boolean favorite;
 @Column(columnDefinition = "TEXT")
 private String log;

 // SHELVES
 @ManyToMany(mappedBy = "logs")
 @ToString.Exclude
 private List<TagsEntity> tags;


 @Override
 public final int hashCode() {
  return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
 }


 public void setId(Long id) {
  this.id = id;
 }
}
