/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends CrudRepository<LogsEntity, Long> {

 @Query("SELECT r FROM LogsEntity r WHERE r.bookIsbn = :isbn")
 LogsEntity findByIsbn(@Param("isbn") String isbn);

 @Query("SELECT r FROM LogsEntity r WHERE r.bookIsbn = :isbn")
 List<LogsEntity> findAllByIsbn(@Param("isbn") String isbn);
}


