/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.reviews;

import nl.marisabel.imReadingAPI.exceptions.books.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewsServiceImplementation implements ReviewsService {

 private final ReviewsRepository reviewsRepository;
 private final DataExistingCheck dataExistingCheck;

 @Autowired
 public ReviewsServiceImplementation(ReviewsRepository reviewsRepository, DataExistingCheck dataExistingCheck) {
  this.reviewsRepository = reviewsRepository;
  this.dataExistingCheck = dataExistingCheck;
 }

 @Override
 public Long getReviewIdForBook(String isbn) {
  ReviewsEntity reviewsEntity = reviewsRepository.findByIsbn(isbn);
  if (reviewsEntity != null) {
   return reviewsEntity.getId();
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @Override
 public ReviewsDTO addReviewToBook(ReviewsDTO reviewsDTO) {
  if (dataExistingCheck.doesIsbnExistsInBooks(reviewsDTO.getBookIsbn())) {
   ReviewsEntity entity = dtoToEntity(reviewsDTO);
   ReviewsEntity savedEntity = reviewsRepository.save(entity);
   return entityToDto(savedEntity);
  } else {
   throw new BookNotFoundException(reviewsDTO.getBookIsbn());
  }
 }

 @Override
 public ReviewsDTO getReviewForABook(String isbn) {
  ReviewsEntity reviewsEntity = reviewsRepository.findByIsbn(isbn);
  if (reviewsEntity != null) {
   return entityToDto(reviewsEntity);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @Override
 public ReviewsDTO updateReview(String isbn, ReviewsDTO updatedReviewsDTO) {
  Optional<ReviewsEntity> optionalEntity = reviewsRepository.findById(getReviewIdForBook(isbn));
  if (optionalEntity.isPresent()) {
   ReviewsEntity entity = optionalEntity.get();
   entity.setDate(updatedReviewsDTO.getDate());
   entity.setReview(updatedReviewsDTO.getReview());
   ReviewsEntity updatedEntity = reviewsRepository.save(entity);
   return entityToDto(updatedEntity);
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @Override
 public boolean deleteReview(String isbn) {
  Optional<ReviewsEntity> optionalEntity = reviewsRepository.findById(getReviewIdForBook(isbn));
  if (optionalEntity.isPresent()) {
   ReviewsEntity entity = optionalEntity.get();
   reviewsRepository.delete(entity);
   return true;
  } else {
   throw new IdNotFoundException(getReviewIdForBook(isbn));
  }
 }

 public ReviewsDTO entityToDto(ReviewsEntity entity) {
  ReviewsDTO dto = new ReviewsDTO();
  dto.setBookIsbn(entity.getBookIsbn());
  dto.setDate(entity.getDate());
  dto.setReview(entity.getReview());
  return dto;
 }

 public ReviewsEntity dtoToEntity(ReviewsDTO dto) {
  ReviewsEntity entity = new ReviewsEntity();
  entity.setBookIsbn(dto.getBookIsbn());
  entity.setDate(dto.getDate());
  entity.setReview(dto.getReview());
  return entity;
 }
}
