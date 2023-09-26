/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.shelves;

import io.swagger.v3.oas.annotations.tags.Tag;
import nl.marisabel.imReadingAPI.customResponse.ResponseHandler;
import nl.marisabel.imReadingAPI.exceptions.*;
import nl.marisabel.imReadingAPI.exceptions.books.ShelfNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.DuplicateShelfException;
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



 @ExceptionHandler(DuplicateShelfException.class)
 public ResponseEntity<CustomResponses> handleDuplicateShelfException(DuplicateShelfException ex) {
  CustomResponses errorResponse = new CustomResponses(803, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
 }


 @PostMapping
 public ResponseEntity<ShelvesDTO> createShelf(@RequestBody ShelvesDTO shelf) {
  try {
   if (shelvesService.existsById(shelf.getName())) {
    throw new DuplicateShelfException(shelf.getName());
   }
   ShelvesDTO createdShelf = shelvesService.createShelf(shelf);
   return new ResponseEntity<>(createdShelf, HttpStatus.CREATED);
  } catch (DuplicateShelfException ex) {
   throw new DuplicateShelfException(shelf.getName());
  }
 }

 @GetMapping("/{name}")
 public ResponseEntity<?> getShelfById(@PathVariable String name) {
  ShelvesDTO shelf = shelvesService.getShelfById(name);
  if (shelf == null) {
   throw new ShelfNotFoundException(name);
  }
  return new ResponseEntity<>(shelf, HttpStatus.OK);
 }

 @GetMapping
 public List<ShelvesDTO> getAllShelves() {
  return shelvesService.getAllShelves();
 }


 @DeleteMapping("/{name}")
 public ResponseEntity<?> deleteShelf(@PathVariable String name) {
  if(shelvesService.existsById(name)) {
   shelvesService.deleteShelf(name);
   return ResponseHandler.generateResponse("Deleted!", HttpStatus.OK, name);
  }
   else {
    throw new ShelfNotFoundException(name);
  }
 }

}
