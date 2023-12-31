/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions.dataValidation;

import nl.marisabel.imReadingAPI.domains.books.BooksEntity;
import nl.marisabel.imReadingAPI.domains.books.BooksRepository;
import nl.marisabel.imReadingAPI.domains.logs.LogsEntity;
import nl.marisabel.imReadingAPI.domains.logs.LogsRepository;
import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataEntity;
import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataRepository;
import nl.marisabel.imReadingAPI.domains.reviews.ReviewsEntity;
import nl.marisabel.imReadingAPI.domains.reviews.ReviewsRepository;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesEntity;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataExistingCheck {

 private final ReadingDataRepository readingDataRepository;
 private final BooksRepository booksRepository;
 private final ShelvesRepository shelvesRepository;
 private final LogsRepository logsRepository;
 private final ReviewsRepository reviewsRepository;

 public DataExistingCheck(ReadingDataRepository readingDataRepository,
                          BooksRepository booksRepository,
                          ReviewsRepository reviewsRepository,
                          ShelvesRepository shelvesRepository,
                          LogsRepository logsRepository, ReviewsRepository reviewsRepository1) {
  this.readingDataRepository = readingDataRepository;
  this.booksRepository = booksRepository;
  this.shelvesRepository = shelvesRepository;
  this.logsRepository = logsRepository;
  this.reviewsRepository = reviewsRepository1;
 }


 // CHECK FOR ISBN

 public boolean doesIsbnExistsInBooks(String isbn) {
  Optional<BooksEntity> book = booksRepository.findById(isbn);
  return book.isPresent();
 }

 public boolean doesIsbnExistsInReadingData(String isbn) {
  Optional<ReadingDataEntity> book = readingDataRepository.findById(isbn);
  return book.isPresent();
 }

 public boolean doesIsbnExistsInLogs(String isbn) {
  LogsEntity book = logsRepository.findByIsbn(isbn);
  return book != null;
 }

public boolean doesIsbnExistsInReviews(String isbn) {
  ReviewsEntity book = reviewsRepository.findByIsbn(isbn);
  return book != null;
}

 // CHECK FOR IDs

 public boolean doesReadingDataExists(String isbn) {
  Optional<ReadingDataEntity> anId = readingDataRepository.findById(isbn);
  return anId.isPresent();
 }




 public boolean doesShelfExist(String name) {
  Optional<ShelvesEntity> anId = shelvesRepository.findById(name);
  return anId.isPresent();
 }


}
