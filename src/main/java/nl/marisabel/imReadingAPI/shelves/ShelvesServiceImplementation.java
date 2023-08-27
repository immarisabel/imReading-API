package nl.marisabel.imReadingAPI.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelvesServiceImplementation implements ShelvesService {

 private final ShelvesRepository shelvesRepository;

 @Autowired
 public ShelvesServiceImplementation(ShelvesRepository shelvesRepository) {
  this.shelvesRepository = shelvesRepository;
 }

 @Override
 public ShelvesEntity createShelf(ShelvesEntity shelf) {
  return shelvesRepository.save(shelf);
 }

 @Override
 public ShelvesEntity getShelfById(Long id) {
  return shelvesRepository.findById(id).orElse(null);
 }

 @Override
 public List<ShelvesEntity> getAllShelves() {
  return (List<ShelvesEntity>) shelvesRepository.findAll();
 }

 @Override
 public ShelvesEntity updateShelf(Long id, ShelvesEntity updatedShelf) {
  if (shelvesRepository.existsById(id)) {
   updatedShelf.setId(id);
   return shelvesRepository.save(updatedShelf);
  }
  return null;
 }

 @Override
 public void deleteShelf(Long id) {
  shelvesRepository.deleteById(id);
 }
}
