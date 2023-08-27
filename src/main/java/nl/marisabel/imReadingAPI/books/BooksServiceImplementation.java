package nl.marisabel.imReadingAPI.books;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BooksServiceImplementation implements BooksService {

 private final BooksRepository booksRepository;

 public BooksServiceImplementation(BooksRepository booksRepository) {
  this.booksRepository = booksRepository;
 }

 @Override
 public BookDTO createBook(BookDTO bookDTO) {
  BooksEntity bookEntity = convertDtoToEntity(bookDTO);
  BooksEntity savedEntity = booksRepository.save(bookEntity);
  return convertEntityToDto(savedEntity);
 }

 @Override
 public BookDTO getBookByIsbn(String isbn) {
  BooksEntity entity = booksRepository.findById(isbn).orElse(null);
  if (entity != null) {
   return convertEntityToDto(entity);
  }
  return null;
 }

 @Override
 public List<BookDTO> getAllBooks() {
  List<BooksEntity> entityList = (List<BooksEntity>) booksRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }

 @Override
 public BookDTO updateBook(String isbn, BookDTO updatedBookDTO) {
  if (booksRepository.existsById(isbn)) {
   BooksEntity updatedEntity = convertDtoToEntity(updatedBookDTO);
   updatedEntity.setIsbn(isbn); // Update the ID

   BooksEntity savedEntity = booksRepository.save(updatedEntity);
   return convertEntityToDto(savedEntity);
  }
  return null;
 }

 @Override
 public void deleteBook(String isbn) {
  booksRepository.deleteById(isbn);
 }

 private BooksEntity convertDtoToEntity(BookDTO dto) {
  return BooksEntity.builder()
          .isbn(dto.getIsbn())
          .title(dto.getTitle())
          .author(dto.getAuthor())
          .pages(dto.getPages())
          .thumbnailUrl(dto.getThumbnailUrl())
          .build();
 }

 private BookDTO convertEntityToDto(BooksEntity entity) {
  return BookDTO.builder()
          .isbn(entity.getIsbn())
          .title(entity.getTitle())
          .author(entity.getAuthor())
          .pages(entity.getPages())
          .thumbnailUrl(entity.getThumbnailUrl())
          .build();
 }

 private List<BookDTO> convertEntityListToDtoList(List<BooksEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }
}
