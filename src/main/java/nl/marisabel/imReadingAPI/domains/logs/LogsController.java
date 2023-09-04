/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/logs/"})
@Tag(name = "logs service", description = "manage the logs for each book")
public class LogsController {

 private final LogsServiceImplementation logsService;

 public LogsController(LogsServiceImplementation logsService) {
  this.logsService = logsService;
 }

 @PostMapping
 public ResponseEntity<LogsDTO> addLog(@RequestBody LogsDTO logsDTO) {
  LogsDTO addedLog = logsService.addLogToBook(logsDTO);
  if (addedLog != null) {
   return new ResponseEntity<>(addedLog, HttpStatus.CREATED);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 // TODO GET methods not working IllegalStateException: Ambiguous handler methods mapped for

 @GetMapping("/{isbn}")
 public ResponseEntity<LogsDTO> getAllLogsForABookByIsbn(@PathVariable String isbn) {
  LogsDTO logsDTO = logsService.getAllLogsForABook(isbn);
  if (logsDTO != null) {
   return new ResponseEntity<>(logsDTO, HttpStatus.OK);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @GetMapping("/{id}")
 public ResponseEntity<LogsDTO> getLogById(@PathVariable Long id) {
  LogsDTO logsDTO = logsService.getLogById(id);
  if (logsDTO != null) {
   return new ResponseEntity<>(logsDTO, HttpStatus.OK);
  } else {
   throw new IdNotFoundException(id);
  }
 }

 @PutMapping("/{id}")
 public ResponseEntity<LogsDTO> updateLog(@PathVariable Long id, @RequestBody LogsDTO updatedLogsDTO) {
  LogsDTO updatedReadingData = logsService.updateLog(id, updatedLogsDTO);
   if (updatedReadingData == null) {
    throw new IdNotFoundException(id);
   }
  return new ResponseEntity<>(updatedReadingData, HttpStatus.OK);
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteLog(@PathVariable Long id) {
  boolean deleted = logsService.deleteLog(id);
  if (deleted) {
   return ResponseEntity.ok("Data deleted.");
  }
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Reading data with ID " + id + " not found.");
 }
}

