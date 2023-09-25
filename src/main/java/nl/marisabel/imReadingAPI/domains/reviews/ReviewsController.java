/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.reviews;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.java.Log;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
@RequestMapping(value = {"${url.mapping.v1}/review/{isbn}"})
@Tag(name = "5. review service", description = "manage the review for each book")
public class ReviewsController {

 private final ReviewsServiceImplementation reviewsService;

 public ReviewsController(ReviewsServiceImplementation reviewsService) {
  this.reviewsService = reviewsService;
 }

 @PostMapping
 public ResponseEntity<ReviewsDTO> addReview(@RequestBody ReviewsDTO reviewsDTO) {

  ReviewsDTO addedLog = reviewsService.addReviewToBook(reviewsDTO);
  System.out.println(reviewsDTO);
  if (addedLog != null) {
   return new ResponseEntity<>(addedLog, HttpStatus.CREATED);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @GetMapping
 public ResponseEntity<ReviewsDTO> getAllReviewsForABook(@PathVariable String isbn) {
  ReviewsDTO reviewsDTO = reviewsService.getReviewForABook(isbn);
  if (reviewsDTO != null) {
   return new ResponseEntity<>(reviewsDTO, HttpStatus.OK);
  } else {
   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
 }

 @PutMapping
 public ResponseEntity<ReviewsDTO> updateReview(@PathVariable String isbn, @RequestBody ReviewsDTO updatedReviewsDTO) {
  ReviewsDTO updateReview = reviewsService.updateReview(isbn, updatedReviewsDTO);
  if (updateReview != null) {
   return new ResponseEntity<>(updateReview, HttpStatus.OK);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @DeleteMapping
 public ResponseEntity<String> deleteReview(@PathVariable String isbn) {
  boolean deleted = reviewsService.deleteReview(isbn);
  if (deleted) {
   return ResponseEntity.ok("Review deleted.");
  }
  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Deleting Failed: Review for book " + isbn + " not found.");
 }

}

