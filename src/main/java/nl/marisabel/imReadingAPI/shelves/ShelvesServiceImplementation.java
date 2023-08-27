/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.shelves;

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
 public ShelvesDTO getShelfById(Long id) {
  ShelvesEntity entity = shelvesRepository.findById(id).orElse(null);
  if (entity != null) {
   return convertEntityToDto(entity);
  }
  return null;
 }

 @Override
 public List<ShelvesDTO> getAllShelves() {
  List<ShelvesEntity> entityList = (List<ShelvesEntity>) shelvesRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }

 @Override
 public ShelvesDTO updateShelf(Long id, ShelvesDTO updatedShelfDTO) {
  if (shelvesRepository.existsById(id)) {
   ShelvesEntity updatedEntity = convertDtoToEntity(updatedShelfDTO);
   updatedEntity.setId(id);

   ShelvesEntity savedEntity = shelvesRepository.save(updatedEntity);
   return convertEntityToDto(savedEntity);
  }
  return null;
 }


 @Override
 public boolean deleteShelf(Long id) {
  shelvesRepository.deleteById(id);
  return false;
 }


 private ShelvesEntity convertDtoToEntity(ShelvesDTO dto) {
  return ShelvesEntity.builder()
          .id(dto.getId())
          .name(dto.getName())
          .build();
 }


 private ShelvesDTO convertEntityToDto(ShelvesEntity entity) {
  return ShelvesDTO.builder()
          .id(entity.getId())
          .name(entity.getName())
          .build();
 }

 private List<ShelvesDTO> convertEntityListToDtoList(List<ShelvesEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }

 public boolean isShelfNameDuplicate(String shelfName) {
  ShelvesDTO existingShelf = getShelfByName(shelfName);
  return existingShelf != null;
 }

 public ShelvesDTO convertEntityToDTO(ShelvesEntity entity) {
  ShelvesDTO dto = new ShelvesDTO();
  dto.setId(entity.getId());
  dto.setName(entity.getName());
  return dto;
 }


 @Override
 public ShelvesDTO getShelfByName(String name) {
  ShelvesEntity shelfEntity = shelvesRepository.findByName(name);

  if (shelfEntity != null) {
   return convertEntityToDTO(shelfEntity);
  }

  return null;
 }



}
