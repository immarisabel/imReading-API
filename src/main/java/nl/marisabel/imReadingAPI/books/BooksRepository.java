package nl.marisabel.imReadingAPI.books;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends CrudRepository<BooksEntity, String> {
}
