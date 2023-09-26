/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.readingData;

import nl.marisabel.imReadingAPI.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadingDataServiceImplementation implements ReadingDataService {

 private final ReadingDataRepository readingDataRepository;
 private final DataExistingCheck dataExistingCheck;

 @Autowired
 public ReadingDataServiceImplementation(ReadingDataRepository readingDataRepository, DataExistingCheck dataExistingCheck) {
  this.readingDataRepository = readingDataRepository;
  this.dataExistingCheck = dataExistingCheck;
 }

 @Override
 public ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingDataDTO) {
  if (dataExistingCheck.doesIsbnExistsInBooks(readingDataDTO.getBookIsbn())) {
   ReadingDataEntity entity = dtoToEntity(readingDataDTO);
   ReadingDataEntity savedEntity = readingDataRepository.save(entity);
   return entityToDto(savedEntity);
  } else {
   throw new BookNotFoundException(readingDataDTO.getBookIsbn());
  }
 }

 @Override
 public ReadingDataDTO getAllReadingDataForABook(String isbn) {
  Optional<ReadingDataEntity> readingDataEntity = readingDataRepository.findById(isbn);
  if (readingDataEntity.isPresent()) {
   return entityToDto(readingDataEntity.get());
  } else {
   throw new NothingFoundWithIsbnException(isbn);
  }
 }

 @Override
 public ReadingDataDTO updateReadingData(String isbn, ReadingDataDTO updatedReadingDataDTO) {
  Optional<ReadingDataEntity> optionalEntity = readingDataRepository.findById(isbn);

  if (optionalEntity.isPresent()) {
   ReadingDataEntity entity = optionalEntity.get();
   entity.setStartedDate(updatedReadingDataDTO.getStartedDate());
   entity.setFinishedDate(updatedReadingDataDTO.getFinishedDate());
   entity.setStatus(updatedReadingDataDTO.getStatus());
   entity.setCurrentPage(updatedReadingDataDTO.getCurrentPage());
   entity.setRating(updatedReadingDataDTO.getRating());
   entity.setFavorite(updatedReadingDataDTO.isFavorite());

   ReadingDataEntity updatedEntity = readingDataRepository.save(entity);
   return entityToDto(updatedEntity);
  } else {
   throw new DataNotFoundByIsbnException(isbn);
  }
 }

 @Override
 public boolean deleteReadingData(String isbn) {
  Optional<ReadingDataEntity> optionalEntity = readingDataRepository.findById(isbn);
  if (optionalEntity.isPresent()) {
   ReadingDataEntity entity = optionalEntity.get();
   readingDataRepository.delete(entity);
   return true;
  } else {
   throw new DataNotFoundByIsbnException(isbn);
  }
 }


 public ReadingDataDTO entityToDto(ReadingDataEntity entity) {
  ReadingDataDTO dto = new ReadingDataDTO();
  dto.setBookIsbn(entity.getBookIsbn());
  dto.setStartedDate(entity.getStartedDate());
  dto.setFinishedDate(entity.getFinishedDate());
  dto.setStatus(entity.getStatus());
  dto.setCurrentPage(entity.getCurrentPage());
  dto.setRating(entity.getRating());
  dto.setFavorite(entity.isFavorite());
  return dto;
 }

 public ReadingDataEntity dtoToEntity(ReadingDataDTO dto) {
  ReadingDataEntity entity = new ReadingDataEntity();
  entity.setBookIsbn(dto.getBookIsbn());
  entity.setStartedDate(dto.getStartedDate());
  entity.setFinishedDate(dto.getFinishedDate());
  entity.setStatus(dto.getStatus());
  entity.setCurrentPage(dto.getCurrentPage());
  entity.setRating(dto.getRating());
  entity.setFavorite(dto.isFavorite());
  return entity;
 }
}
