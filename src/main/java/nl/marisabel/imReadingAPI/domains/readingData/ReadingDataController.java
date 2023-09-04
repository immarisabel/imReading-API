/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/reading/"})
@Tag(name = "reading data service", description = "manage the reading data for each book")
public class ReadingDataController {

 private final ReadingDataServiceImplementation readingDataService;

 public ReadingDataController(ReadingDataServiceImplementation readingDataService) {
  this.readingDataService = readingDataService;
 }

 @PostMapping
 public ResponseEntity<ReadingDataDTO> addReadingData(@RequestBody ReadingDataDTO readingDataDTO) {
  ReadingDataDTO addedReadingData = readingDataService.addReadingDataToBook(readingDataDTO);
  if (addedReadingData != null) {
   return new ResponseEntity<>(addedReadingData, HttpStatus.CREATED);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @GetMapping("/{isbn}")
 public ResponseEntity<ReadingDataDTO> getAllReadingDataForABookByIsbn(@PathVariable String isbn) {
  ReadingDataDTO readingDataDTO = readingDataService.getAllReadingDataForABook(isbn);
  if (readingDataDTO != null) {
   return new ResponseEntity<>(readingDataDTO, HttpStatus.OK);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @PutMapping("/{id}")
 public ResponseEntity<ReadingDataDTO> updateReadingData(@PathVariable Long id, @RequestBody ReadingDataDTO updatedReadingDataDTO) {
  ReadingDataDTO updatedReadingData = readingDataService.updateReadingData(id, updatedReadingDataDTO);
  if (updatedReadingData != null) {
   return new ResponseEntity<>(updatedReadingData, HttpStatus.OK);
  } else {
   if (updatedReadingData == null) {
    throw new IdNotFoundException(id);
   }
  }
  return null;
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<String> deleteReadingData(@PathVariable Long id) {
  boolean deleted = readingDataService.eraseReadingData(id);
  if (deleted) {
   return ResponseEntity.ok("Data deleted.");
  }
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Reading data with ID " + id + " not found.");
 }
}

