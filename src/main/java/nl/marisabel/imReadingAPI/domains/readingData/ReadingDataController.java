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
@RequestMapping(value = {"${url.mapping.v1}/book/{isbn}/reading"})
@Tag(name = "reading data service", description = "manage the reading data for each book")
public class ReadingDataController {

 private final ReadingDataService readingDataService;

 public ReadingDataController(ReadingDataService readingDataService) {
  this.readingDataService = readingDataService;
 }

 @PostMapping
 public ResponseEntity<ReadingDataDTO> addReadingData(@PathVariable String isbn, @RequestBody ReadingDataDTO readingDataDTO) {
  readingDataDTO.getBook_isbn();

  ReadingDataDTO addedReadingData = readingDataService.addReadingDataToBook(readingDataDTO);
  if (addedReadingData != null) {
   return new ResponseEntity<>(addedReadingData, HttpStatus.CREATED);
  } else {
   // TODO implement 601
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @GetMapping("/{readingDataId}")
 public ResponseEntity<ReadingDataDTO> getReadingDataById(@PathVariable String isbn, @PathVariable Long readingDataId) {
  ReadingDataDTO readingDataDTO = readingDataService.getReadingDataById(readingDataId);
  if (readingDataDTO != null) {
   return new ResponseEntity<>(readingDataDTO, HttpStatus.OK);
  } else {
   // TODO implement 601
   // TODO implement 901 - no data found
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @PutMapping("/{readingDataId}")
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

 @DeleteMapping("/{readingDataId}")
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

