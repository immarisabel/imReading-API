package nl.marisabel.imReadingAPI.shelves;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {"${url.mapping.v1}/shelves"})
public class ShelvesController {

 @Autowired
 private ShelvesServiceImplementation shelvesService;

 /**
  * REQUEST BODY EXAMPLE
  * {
  * "name": "Sample Shelf"
  * }
  *
  * @param shelf
  * @return Response 200
  */
 @PostMapping
 public ShelvesEntity createShelf(@RequestBody ShelvesEntity shelf) {
  return shelvesService.createShelf(shelf);
 }

 @GetMapping("/{id}")
 public ShelvesEntity getShelfById(@PathVariable Long id) {
  return shelvesService.getShelfById(id);
 }

 @GetMapping
 public List<ShelvesEntity> getAllShelves() {
  return shelvesService.getAllShelves();
 }

 @PutMapping("/{id}")
 public ShelvesEntity updateShelf(@PathVariable Long id, @RequestBody ShelvesEntity updatedShelf) {
  return shelvesService.updateShelf(id, updatedShelf);
 }

 @DeleteMapping("/{id}")
 public void deleteShelf(@PathVariable Long id) {
  shelvesService.deleteShelf(id);
 }
}
