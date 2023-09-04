/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.tags;

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
@RequestMapping(value = {"${url.mapping.v1}/tags"})
@Tag(name = "tags service", description = "manage the tags on the database")
public class TagsController {

 @Autowired
 private TagsServiceImplementation tagsService;

 @ExceptionHandler(IdNotFoundException.class)
 public ResponseEntity<CustomResponses> handleTagNotFound(IdNotFoundException ex) {
  // TODO fix the error codes for 2 more global one (ID not found vs ISBN not found)
  CustomResponses errorResponse = new CustomResponses(801, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
 }

 @ExceptionHandler(DuplicateShelfException.class)
 public ResponseEntity<CustomResponses> handleDuplicateTagsfException(DuplicateShelfException ex) {
  // TODO fix the error codes for a more global one (name duplicates)
  CustomResponses errorResponse = new CustomResponses(803, ex.getMessage());
  return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
 }


 @PostMapping
 public ResponseEntity<TagsDTO> createTag(@RequestBody TagsDTO tags) {
  try {
   if (tagsService.isNameDuplicate(tags.getName())) {
    throw new DuplicateShelfException(tags.getName());
   }
   TagsDTO createdTag = tagsService.createTag(tags);
   return new ResponseEntity<>(createdTag, HttpStatus.CREATED);
  } catch (DuplicateShelfException ex) {
   throw new DuplicateShelfException(tags.getName());
  }
 }

 @GetMapping("/{id}")
 public ResponseEntity<?> getTagById(@PathVariable Long id) {
  TagsDTO tag = tagsService.getTagById(id);

  if (tag == null) {
   throw new IdNotFoundException(id);
  }

  return new ResponseEntity<>(tag, HttpStatus.OK);
 }

 @GetMapping
 public List<TagsDTO> getAllTags() {
  return tagsService.getAllTags();
 }


 @PutMapping("/{id}")
 public ResponseEntity<?> updateTag(@PathVariable Long id, @RequestBody TagsDTO updatedTag) {
  if (tagsService.isNameDuplicate(updatedTag.getName())) {
   throw new DuplicateShelfException(updatedTag.getName());
  }

  TagsDTO updated = tagsService.updateTag(id, updatedTag);

  if (updated == null) {
   throw new IdNotFoundException(id);
  }

  return new ResponseEntity<>(updated, HttpStatus.OK);
 }


 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteTag(@PathVariable Long id) {
  boolean deleted = tagsService.deleteTag(id);

  if (!deleted) {
   throw new IdNotFoundException(id);
  }

  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
}
