/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.logs;

import nl.marisabel.imReadingAPI.domains.books.BooksEntity;
import nl.marisabel.imReadingAPI.exceptions.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.DataExistingCheck;
import nl.marisabel.imReadingAPI.exceptions.IdNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.NothingFoundWithIsbnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogsServiceImplementation implements LogsService {

 private final LogsRepository logsRepository;
 private final DataExistingCheck dataExistingCheck;


 @Autowired
 public LogsServiceImplementation(LogsRepository logsRepository, DataExistingCheck dataExistingCheck) {
  this.logsRepository = logsRepository;
  this.dataExistingCheck = dataExistingCheck;
 }

 @Override
 public LogsDTO addLogToBook(LogsDTO logsDTO) {
  if (dataExistingCheck.doesIsbnExistsInBooks(logsDTO.getBookIsbn())) {
   LogsEntity entity = dtoToEntity(logsDTO);
  LogsEntity savedEntity = logsRepository.save(entity);
  return entityToDto(savedEntity);
  } else {
   throw new BookNotFoundException(logsDTO.getBookIsbn());
  }
 }

 @Override
 public LogsDTO getAllLogsForABook(String isbn) {
  LogsEntity logsEntity = logsRepository.findByIsbn(isbn);
  if (logsEntity != null) {
   return entityToDto(logsEntity);
  } else {
   throw new NothingFoundWithIsbnException(isbn);
  }
 }

 @Override
 public LogsDTO getLogById(Long id) {
  LogsEntity entity = logsRepository.findById(id).orElse(null);
  if (entity != null) {
   return entityToDto(entity);
  }
  throw new IdNotFoundException(id);
 }


 @Override
 public LogsDTO updateLog(Long id, LogsDTO updatedLogsDTO) {
  Optional<LogsEntity> optionalEntity = logsRepository.findById(id);

  if (optionalEntity.isPresent()) {
   LogsEntity entity = optionalEntity.get();
   entity.setDate(updatedLogsDTO.getDate());
   entity.setPage(updatedLogsDTO.getPage());
   entity.setBookIsbn(updatedLogsDTO.getBookIsbn());
   entity.setFavorite(updatedLogsDTO.isFavorite());
   entity.setLog(updatedLogsDTO.getLog());
   LogsEntity updatedEntity = logsRepository.save(entity);
   return entityToDto(updatedEntity);
  } else {
   throw new IdNotFoundException(id);
  }
 }

 @Override
 public boolean deleteLog(Long id) {
  Optional<LogsEntity> optionalEntity = logsRepository.findById(id);

  if (optionalEntity.isPresent()) {
   LogsEntity entity = optionalEntity.get();
   logsRepository.delete(entity);
   return true;
  } else {
   throw new IdNotFoundException(id);
  }
 }


 public LogsDTO entityToDto(LogsEntity entity) {
  LogsDTO dto = new LogsDTO();
  dto.setBookIsbn(entity.getBookIsbn());
  dto.setDate(entity.getDate());
  dto.setPage(entity.getPage());
  dto.setFavorite(entity.isFavorite());
  dto.setLog(entity.getLog());
  return dto;
 }

 public LogsEntity dtoToEntity(LogsDTO dto) {
  LogsEntity entity = new LogsEntity();
  entity.setBookIsbn(dto.getBookIsbn());
  entity.setDate(dto.getDate());
  entity.setPage(dto.getPage());
  entity.setFavorite(dto.isFavorite());
  entity.setLog(dto.getLog());
  return entity;
 }
}
