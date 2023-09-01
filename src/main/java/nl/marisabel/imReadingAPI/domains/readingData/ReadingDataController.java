/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.customResponse.ResponseHandler;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/reading/{isbn}"})
@Tag(name = "reading data service", description = "manage the reading data for each book")
public class ReadingDataController {

 private final ReadingDataService readingDataService;

 public ReadingDataController(ReadingDataService readingDataService) {
  this.readingDataService = readingDataService;
 }

 @PostMapping
 public ResponseEntity<ReadingDataDTO> addReadingData(@RequestBody ReadingDataDTO readingDataDTO) {
  readingDataDTO.getBookIsbn();

  ReadingDataDTO addedReadingData = readingDataService.addReadingDataToBook(readingDataDTO);
  if (addedReadingData != null) {
   return new ResponseEntity<>(addedReadingData, HttpStatus.CREATED);
  } else {
   // TODO implement 601
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @GetMapping
 public ResponseEntity<ReadingDataDTO> getAllReadingDataForABookByIsbn(@PathVariable String isbn) {
  ReadingDataDTO readingDataDTO = readingDataService.getAllReadingDataForABook(isbn);
  if (readingDataDTO != null) {
   return new ResponseEntity<>(readingDataDTO, HttpStatus.OK);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @PutMapping
 public ResponseEntity<ReadingDataDTO> updateReadingData(@PathVariable String isbn, @PathVariable Long readingDataId, @RequestBody ReadingDataDTO updatedReadingDataDTO) {
  ReadingDataDTO updatedReadingData = readingDataService.updateReadingData(readingDataId, updatedReadingDataDTO);
  if (updatedReadingData != null) {
   return new ResponseEntity<>(updatedReadingData, HttpStatus.OK);
  } else {
   // TODO implement 601
   // TODO implement 901 - no data found
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @DeleteMapping
 public ResponseEntity<Void> deleteReadingData(@PathVariable String isbn, @PathVariable Long readingDataId) {
  boolean deleted = readingDataService.eraseReadingData(readingDataId);
  if (deleted) {
   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } else {
   // TODO implement 601
   // TODO implement 901 - no data found
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }
}

