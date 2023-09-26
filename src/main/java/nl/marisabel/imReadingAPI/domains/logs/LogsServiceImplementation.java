/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.logs;

import nl.marisabel.imReadingAPI.exceptions.books.BookNotFoundException;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.DataExistingCheck;
import nl.marisabel.imReadingAPI.exceptions.dataValidation.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LogsServiceImplementation implements LogsService {
 private final DataExistingCheck dataExistingCheck;
 private final LogsRepository logsRepository;

 @Autowired
 public LogsServiceImplementation(DataExistingCheck dataExistingCheck, LogsRepository logsRepository) {
  this.dataExistingCheck = dataExistingCheck;
  this.logsRepository = logsRepository;
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
 public List<LogsDTO> getAllLogsForABook(String isbn) {
  List<LogsEntity> logsEntity = logsRepository.findAllByIsbn(isbn);

  if (!logsEntity.isEmpty()) {
   return logsEntity.stream()
           .map(this::entityToDto)
           .collect(Collectors.toList());
  } else {
   throw new BookNotFoundException(isbn);
  }
 }

 @Override
 public LogsDTO getLogById(Long id) {
  return entityToDto(Objects.requireNonNull(logsRepository.findById(id).orElse(null)));
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
