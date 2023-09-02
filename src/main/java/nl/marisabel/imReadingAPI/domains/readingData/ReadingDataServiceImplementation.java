/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.readingData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReadingDataServiceImplementation implements ReadingDataService {

 private final ReadingDataRepository readingDataRepository;

 @Autowired
 public ReadingDataServiceImplementation(ReadingDataRepository readingDataRepository) {
  this.readingDataRepository = readingDataRepository;
 }

 @Override
 public ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingDataDTO) {
  ReadingDataEntity entity = dtoToEntity(readingDataDTO);
  ReadingDataEntity savedEntity = readingDataRepository.save(entity);
  return entityToDto(savedEntity);
 }

 @Override
 public ReadingDataDTO getAllReadingDataForABook(String isbn) {
  ReadingDataEntity readingDataEntity = readingDataRepository.findByIsbn(isbn);
  if (readingDataEntity != null) {
   return entityToDto(readingDataEntity);
  } else {
   return null;
  }
 }

 @Override
 public ReadingDataDTO updateReadingData(Long id, ReadingDataDTO updatedReadingDataDTO) {
  Optional<ReadingDataEntity> optionalEntity = readingDataRepository.findById(id);

  if (optionalEntity.isPresent()) {
   ReadingDataEntity entity = optionalEntity.get();
   // Update fields with values from updatedReadingDataDTO
   entity.setStartedDate(updatedReadingDataDTO.getStartedDate());
   entity.setFinishedDate(updatedReadingDataDTO.getFinishedDate());
   entity.setStatus(updatedReadingDataDTO.getStatus());
   entity.setCurrentPage(updatedReadingDataDTO.getCurrentPage());
   entity.setRating(updatedReadingDataDTO.getRating());
   entity.setFavorite(updatedReadingDataDTO.isFavorite());

   ReadingDataEntity updatedEntity = readingDataRepository.save(entity);
   return entityToDto(updatedEntity);
  } else {
// TODO handle 601 & 901
   return null;
  }
 }

 @Override
 public boolean eraseReadingData(Long id) {
  Optional<ReadingDataEntity> optionalEntity = readingDataRepository.findById(id);

  if (optionalEntity.isPresent()) {
   ReadingDataEntity entity = optionalEntity.get();
   readingDataRepository.delete(entity);
   return true;
  } else {
// TODO handle 601 & 901
   return false;
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
