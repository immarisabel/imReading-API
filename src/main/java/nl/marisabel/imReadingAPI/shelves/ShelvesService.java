package nl.marisabel.imReadingAPI.shelves;

import nl.marisabel.imReadingAPI.books.BooksEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelvesService {
 ShelvesDTO createShelf(ShelvesDTO shelf);

 ShelvesDTO getShelfById(Long id);

 List<ShelvesDTO> getAllShelves();

 ShelvesDTO updateShelf(Long id, ShelvesDTO updateShelf);

 void deleteShelf(Long id);
}

