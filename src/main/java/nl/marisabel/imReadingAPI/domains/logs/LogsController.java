/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/logs/"})
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

 @GetMapping("/{isbn}")
 public ResponseEntity<List<LogsDTO>> getAllLogsForABookByIsbn(@PathVariable String isbn) {
  List<LogsDTO> logs = logsService.getAllLogsForABook(isbn);

  if (logs.isEmpty()) {
   return ResponseEntity.noContent().build();
  } else {
   return ResponseEntity.ok(logs);
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

