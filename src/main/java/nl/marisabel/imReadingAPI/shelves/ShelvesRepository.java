package nl.marisabel.imReadingAPI.shelves;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelvesRepository extends CrudRepository<ShelvesEntity, Integer> {
}
