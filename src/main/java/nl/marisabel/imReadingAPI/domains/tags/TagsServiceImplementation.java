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


 @Override
 public boolean deleteTag(Long id) {
  tagsRepository.deleteById(id);
  return false;
 }


 private TagsEntity convertDtoToEntity(TagsDTO dto) {
  return TagsEntity.builder()
          .id(dto.getId())
          .name(dto.getName())
          .build();
 }


 private TagsDTO convertEntityToDto(TagsEntity entity) {
  return TagsDTO.builder()
          .id(entity.getId())
          .name(entity.getName())
          .build();
 }

 private List<TagsDTO> convertEntityListToDtoList(List<TagsEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }

 public boolean isNameDuplicate(String tagName) {
  TagsDTO existingShelf = getTagByName(tagName);
  return existingShelf != null;
 }

 public TagsDTO convertEntityToDTO(TagsEntity entity) {
  TagsDTO dto = new TagsDTO();
  dto.setId(entity.getId());
  dto.setName(entity.getName());
  return dto;
 }


 @Override
 public TagsDTO getTagByName(String tagName) {
  TagsEntity shelfEntity = tagsRepository.findByName(tagName);

  if (shelfEntity != null) {
   return convertEntityToDTO(shelfEntity);
  }

  return null;
 }


}
