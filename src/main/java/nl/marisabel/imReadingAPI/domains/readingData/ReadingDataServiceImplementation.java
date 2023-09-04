/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.readingData;

import nl.marisabel.imReadingAPI.domains.googleSearchApi.NoBookFoundException;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.DataExistingCheck;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.NoDataFoundException;
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
  ReadingDataEntity readingDataEntity = readingDataRepository.findByIsbn(isbn);
  if (readingDataEntity != null) {
   return entityToDto(readingDataEntity);
  } else {
   throw new NoDataFoundException(isbn);
  }
 }

 // TODO : PUT readingData puzzle: it should not allow me to modify ISBN but yes content.
 //  If wrong ISBN inserted, it should not modify it and still insert new data to the current ISBN.
 //  I want to handle ISBN in mult. vs ID for editing, in teh cases when I re-read a book.
 //  So Reading data Id1 ID2 belongs to book ISBN XXX.
 //  Current Behaviour:InvalidDataAccessApiUsageException: The given id must not be null] (when not inserting ISBN)
 //  & BookNotFoundException (when inserting ISBN)
 @Override
 public ReadingDataDTO updateReadingData(Long id, ReadingDataDTO updatedReadingDataDTO) {
  Optional<ReadingDataEntity> optionalEntity = readingDataRepository.findById(id);

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
   throw new IdNotFoundException(id);
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
   throw new IdNotFoundException(id);
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
