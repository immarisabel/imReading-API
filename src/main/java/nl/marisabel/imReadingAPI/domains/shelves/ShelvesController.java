/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.shelves;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.marisabel.imReadingAPI.exceptions.CustomResponses;
import nl.marisabel.imReadingAPI.exceptions.DuplicateShelfException;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"${url.mapping.v1}/shelves"})
@Tag(name = "6. shelves service", description = "manage the shelves on the database")
public class ShelvesController {

 @Autowired
 private ShelvesServiceImplementation shelvesService;

 @ExceptionHandler(IdNotFoundException.class)
 public ResponseEntity<CustomResponses> handleBookNotFoundException(IdNotFoundException ex) {
  CustomResponses errorResponse = new CustomResponses(801, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(DuplicateShelfException.class)
 public ResponseEntity<CustomResponses> handleDuplicateShelfException(DuplicateShelfException ex) {
  CustomResponses errorResponse = new CustomResponses(803, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
 }


 @PostMapping
 public ResponseEntity<ShelvesDTO> createShelf(@RequestBody ShelvesDTO shelf) {
  try {
   if (shelvesService.isShelfNameDuplicate(shelf.getName())) {
    throw new DuplicateShelfException(shelf.getName());
   }
   ShelvesDTO createdShelf = shelvesService.createShelf(shelf);
   return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
  } catch (DuplicateShelfException ex) {
   throw new DuplicateShelfException(shelf.getName());
  }
 }

 @GetMapping("/{id}")
 public ResponseEntity<?> getShelfById(@PathVariable Long id) {
  ShelvesDTO shelf = shelvesService.getShelfById(id);

  if (shelf == null) {
   throw new IdNotFoundException(id);
  }

  return new ResponseEntity<>(shelf, HttpStatus.OK);
 }

 @GetMapping
 public List<ShelvesDTO> getAllShelves() {
  return shelvesService.getAllShelves();
 }


 @PutMapping("/{id}")
 public ResponseEntity<?> updateShelf(@PathVariable Long id, @RequestBody ShelvesDTO updatedShelf) {
  if (shelvesService.isShelfNameDuplicate(updatedShelf.getName())) {
   throw new DuplicateShelfException(updatedShelf.getName());
  }

  ShelvesDTO updated = shelvesService.updateShelf(id, updatedShelf);

  if (updated == null) {
   throw new IdNotFoundException(id);
  }

  return new ResponseEntity<>(updated, HttpStatus.OK);
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteShelf(@PathVariable Long id) {
  boolean deleted = shelvesService.deleteShelf(id);
  if (!deleted) {
   throw new IdNotFoundException(id);
  }
  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}
