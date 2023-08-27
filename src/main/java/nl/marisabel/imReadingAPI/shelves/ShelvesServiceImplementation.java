package nl.marisabel.imReadingAPI.shelves;

import nl.marisabel.imReadingAPI.books.BooksDTO;
import nl.marisabel.imReadingAPI.books.BooksEntity;
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
 public void deleteShelf(Long id) {
  shelvesRepository.deleteById(id);
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
}
