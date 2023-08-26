package nl.marisabel.imReadingAPI.shelves;

import nl.marisabel.imReadingAPI.books.BooksEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelvesService {
 ShelvesEntity createShelf(ShelvesEntity shelf);

 ShelvesEntity getShelfById(Long id);

 List<ShelvesEntity> getAllShelves();

 ShelvesEntity updateShelf(Long id, ShelvesEntity updateShelf);

 void deleteShelf(Long id);
}

