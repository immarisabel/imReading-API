/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.logs;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/logs/{isbn}"})
@Tag(name = "logs service", description = "manage the logs for each book")
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
   // TODO implement 601
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

 @PutMapping
 public ResponseEntity<LogsDTO> updateLog(@PathVariable String isbn, @PathVariable Long readingDataId, @RequestBody LogsDTO updatedLogsDTO) {
  LogsDTO updatedReadingData = logsService.updateLog(readingDataId, updatedLogsDTO);
  if (updatedReadingData != null) {
   return new ResponseEntity<>(updatedReadingData, HttpStatus.OK);
  } else {
   // TODO implement 601
   // TODO implement 901 - no data found
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @DeleteMapping
 public ResponseEntity<Void> deleteLog(@PathVariable String isbn, @PathVariable Long readingDataId) {
  boolean deleted = logsService.deleteLog(readingDataId);
  if (deleted) {
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } else {
   // TODO implement 601
   // TODO implement 901 - no data found
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }
}

