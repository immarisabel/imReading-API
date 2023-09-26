/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.shelves;

import nl.marisabel.imReadingAPI.exceptions.books.ShelfNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShelvesServiceImplementation implements ShelvesService {

 private final ShelvesRepository shelvesRepository;

 @Autowired
 public ShelvesServiceImplementation(ShelvesRepository shelvesRepository) {
  this.shelvesRepository = shelvesRepository;
 }


 @Override
 public ShelvesDTO createShelf(ShelvesDTO shelvesDTO) {
  ShelvesEntity shelvesEntity = convertDtoToEntity(shelvesDTO);
  ShelvesEntity savedEntity = shelvesRepository.save(shelvesEntity);
  return convertEntityToDto(savedEntity);
 }

 @Override
 public ShelvesDTO updateShelf(String name, ShelvesDTO updatedShelfDTO) {
  if (shelvesRepository.existsById(name)) {
   ShelvesEntity updatedEntity = convertDtoToEntity(updatedShelfDTO);
   updatedEntity.setName(name);
   ShelvesEntity savedEntity = shelvesRepository.save(updatedEntity);
   return convertEntityToDto(savedEntity);
  }
  throw new ShelfNotFoundException(name);
 }

 @Override
 public ShelvesDTO getShelfById(String name) {
  ShelvesEntity entity = shelvesRepository.findById(name).orElse(null);
  if (entity != null) {
   return convertEntityToDto(entity);
  }
  throw new ShelfNotFoundException(name);
 }

 @Override
 public List<ShelvesDTO> getAllShelves() {
  List<ShelvesEntity> entityList = (List<ShelvesEntity>) shelvesRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }


 @Override
 public boolean deleteShelf(String name) {
  shelvesRepository.deleteById(name);
  return false;
 }

 @Override
 public boolean existsById(String name) {
  return shelvesRepository.existsById(name);
 }


 // Helper methods

 private ShelvesEntity convertDtoToEntity(ShelvesDTO dto) {
  return ShelvesEntity.builder()
          .name(dto.getName())
          .build();
 }


 private ShelvesDTO convertEntityToDto(ShelvesEntity entity) {
  return ShelvesDTO.builder()
          .name(entity.getName())
          .build();
 }

 private List<ShelvesDTO> convertEntityListToDtoList(List<ShelvesEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }


}
