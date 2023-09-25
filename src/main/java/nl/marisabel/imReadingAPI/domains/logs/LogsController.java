/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.domains.reviews.ReviewsDTO;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/logs/{isbn}"})
@Tag(name = "4. logs service", description = "manage the logs for each book")
public class LogsController {

 private final LogsServiceImplementation logsService;

 public LogsController(LogsServiceImplementation logsService) {
  this.logsService = logsService;
 }

 @PostMapping
 public ResponseEntity<LogsDTO> addLog(@RequestBody LogsDTO logsDTO) {

  LogsDTO addedLog = logsService.addLogToBook(logsDTO);
  System.out.println(logsDTO);
  if (addedLog != null) {
   return new ResponseEntity<>(addedLog, HttpStatus.CREATED);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @GetMapping
 public ResponseEntity<LogsDTO> getAllLogsForABookByIsbn(@PathVariable String isbn) {
  LogsDTO logsDTO = logsService.getAllLogsForABook(isbn);
  if (logsDTO != null) {
   return new ResponseEntity<>(logsDTO, HttpStatus.OK);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @PutMapping("/{id}")
 public ResponseEntity<LogsDTO> updateLog(@PathVariable Long id, @RequestBody LogsDTO updatedLogsDTO) {
  LogsDTO updateLog = logsService.updateLog(id, updatedLogsDTO);
  if (updateLog != null) {
   return new ResponseEntity<>(updateLog, HttpStatus.OK);
  } else {
   throw new IdNotFoundException(id);
  }
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteLog(@PathVariable Long id) {
  boolean deleted = logsService.deleteLog(id);
  if (deleted) {
   return ResponseEntity.ok("Log deleted.");
  }
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Log with ID " + id + " not found.");
 }
}

