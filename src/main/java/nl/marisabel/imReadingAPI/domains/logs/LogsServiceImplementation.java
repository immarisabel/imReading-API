/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */
package nl.marisabel.imReadingAPI.domains.logs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LogsServiceImplementation implements LogsService {

 private final LogsRepository logsRepository;

 @Autowired
 public LogsServiceImplementation(LogsRepository logsRepository) {
  this.logsRepository = logsRepository;
 }

 @Override
 public LogsDTO addLogToBook(LogsDTO logsDTO) {
  LogsEntity entity = dtoToEntity(logsDTO);
  LogsEntity savedEntity = logsRepository.save(entity);
  return entityToDto(savedEntity);
 }

 // TODO never return null!
 @Override
 public LogsDTO getAllLogsForABook(String isbn) {
  LogsEntity logsEntity = logsRepository.findByIsbn(isbn);
  if (logsEntity != null) {
   return entityToDto(logsEntity);
  } else {
   return null;
  }
 }

 // TODO never return null!
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
// TODO handle 601 & 901
   return null;
  }
 }

 @Override
 public boolean deleteLog(Long id) {
  Optional<LogsEntity> optionalEntity = logsRepository.findById(id);

  if (optionalEntity.isPresent()) {
   LogsEntity entity = optionalEntity.get();
   logsRepository.delete(entity);
   // TODO return confirmation of deletion
   return true;
  } else {
// TODO handle 601 & 901
   return false;
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
