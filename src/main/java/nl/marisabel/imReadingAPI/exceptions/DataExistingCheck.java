/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.exceptions;

import nl.marisabel.imReadingAPI.domains.books.BooksEntity;
import nl.marisabel.imReadingAPI.domains.books.BooksRepository;
import nl.marisabel.imReadingAPI.domains.logs.LogsEntity;
import nl.marisabel.imReadingAPI.domains.logs.LogsRepository;
import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataEntity;
import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataRepository;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesEntity;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesRepository;
import nl.marisabel.imReadingAPI.domains.tags.TagsEntity;
import nl.marisabel.imReadingAPI.domains.tags.TagsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataExistingCheck {

 private final ReadingDataRepository readingDataRepository;
 private final BooksRepository booksRepository;
 private final TagsRepository tagsRepository;

 private final ShelvesRepository shelvesRepository;

 private final LogsRepository logsRepository;

 public DataExistingCheck(ReadingDataRepository readingDataRepository,
                          BooksRepository booksRepository,
                          TagsRepository tagsRepository,
                          ShelvesRepository shelvesRepository,
                          LogsRepository logsRepository) {
  this.readingDataRepository = readingDataRepository;
  this.booksRepository = booksRepository;
  this.tagsRepository = tagsRepository;
  this.shelvesRepository = shelvesRepository;
  this.logsRepository = logsRepository;
 }


 // CHECK FOR ISBN

 public boolean doesIsbnExistsInBooks(String isbn) {
  Optional<BooksEntity> book = booksRepository.findById(isbn);
  return book.isPresent();
 }

 public boolean doesIsbnExistsInReadingData(String isbn) {
  ReadingDataEntity book = readingDataRepository.findByIsbn(isbn);
  return book != null;
 }

 public boolean doesIsbnExistsInLogs(String isbn) {
  LogsEntity book = logsRepository.findByIsbn(isbn);
  return book != null;
 }


 // CHECK FOR IDs

 public boolean doesReadingDataExists(Long id) {
  Optional<ReadingDataEntity> anId = readingDataRepository.findById(id);
  return anId.isPresent();
 }

 public boolean doesTagExist(Long id) {
  Optional<TagsEntity> anId = tagsRepository.findById(id);
  return anId.isPresent();
 }

 public boolean doesLogExist(Long id) {
  Optional<LogsEntity> anId = logsRepository.findById(id);
  return anId.isPresent();
 }

 public boolean doesShelfExist(Long id) {
  Optional<ShelvesEntity> anId = shelvesRepository.findById(id);
  return anId.isPresent();
 }


}
