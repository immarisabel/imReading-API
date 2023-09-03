/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.tags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagsServiceImplementation implements TagsService {

 private final TagsRepository tagsRepository;

 @Autowired
 public TagsServiceImplementation(TagsRepository tagsRepository) {
  this.tagsRepository = tagsRepository;
 }


 @Override
 public TagsDTO createTag(TagsDTO tagsDTO) {
  TagsEntity tagsEntity = convertDtoToEntity(tagsDTO);
  TagsEntity savedEntity = tagsRepository.save(tagsEntity);
  return convertEntityToDto(savedEntity);
 }

 // TODO never return null!
 @Override
 public TagsDTO updateTag(Long id, TagsDTO updatedShelfDTO) {
  if (tagsRepository.existsById(id)) {
   TagsEntity updatedEntity = convertDtoToEntity(updatedShelfDTO);
   updatedEntity.setId(id);

   TagsEntity savedEntity = tagsRepository.save(updatedEntity);
   return convertEntityToDto(savedEntity);
  }
  return null;
 }

 // TODO never return null!
 @Override
 public TagsDTO getTagById(Long id) {
  TagsEntity entity = tagsRepository.findById(id).orElse(null);
  if (entity != null) {
   return convertEntityToDto(entity);
  }
  return null;
 }

 @Override
 public List<TagsDTO> getAllTags() {
  List<TagsEntity> entityList = (List<TagsEntity>) tagsRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }


 // TODO controller tag by name /tags/{name}
 // TODO never return null!
 @Override
 public TagsDTO getTagByName(String tagName) {
  TagsEntity shelfEntity = tagsRepository.findByName(tagName);

  if (shelfEntity != null) {
   return convertEntityToDto(shelfEntity);
  }

  return null;
 }


 @Override
 public boolean deleteTag(Long id) {
  tagsRepository.deleteById(id);
  return false;
 }


// Helper methods

 public boolean isNameDuplicate(String tagName) {
  TagsDTO existingShelf = getTagByName(tagName);
  return existingShelf != null;
 }


 private TagsDTO convertEntityToDto(TagsEntity entity) {
  return TagsDTO.builder()
          .id(entity.getId())
          .name(entity.getName())
          .build();
 }

 private TagsEntity convertDtoToEntity(TagsDTO dto) {
  return TagsEntity.builder()
          .id(dto.getId())
          .name(dto.getName())
          .build();
 }
 private List<TagsDTO> convertEntityListToDtoList(List<TagsEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }

}
