/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.books;

import nl.marisabel.imReadingAPI.domains.readingData.ReadingDataEntity;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesEntity;
import nl.marisabel.imReadingAPI.domains.shelves.ShelvesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BooksServiceImplementation implements BooksService {

 private final BooksRepository booksRepository;
 private final ShelvesRepository shelvesRepository;

 public BooksServiceImplementation(BooksRepository booksRepository, ShelvesRepository shelvesRepository) {
  this.booksRepository = booksRepository;
  this.shelvesRepository = shelvesRepository;
 }

 @Override
 public BooksDTO createBook(BooksDTO booksDTO) {
  BooksEntity bookEntity = dtoToEntity(booksDTO);
  BooksEntity savedEntity = booksRepository.save(bookEntity);
  return entityToDto(savedEntity);
 }

 // TODO never return null!
 @Override
 public BooksDTO getBookByIsbn(String isbn) {
  BooksEntity entity = booksRepository.findById(isbn).orElse(null);
  if (entity != null) {
   return entityToDto(entity);
  }
  return null;
 }

 @Override
 public List<BooksDTO> getAllBooks() {
  List<BooksEntity> entityList = (List<BooksEntity>) booksRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }

 // TODO never return null!
 @Override
 public BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO) {
  if (booksRepository.existsById(isbn)) {
   BooksEntity updatedEntity = dtoToEntity(updatedBooksDTO);
   updatedEntity.setIsbn(isbn); // Update the ID

   BooksEntity savedEntity = booksRepository.save(updatedEntity);
   return entityToDto(savedEntity);
  }
  return null;
 }

 @Override
 public boolean deleteBook(String isbn) {
  booksRepository.deleteById(isbn);
  return false;
 }


 private BooksEntity dtoToEntity(BooksDTO dto) {
  List<ShelvesEntity> shelvesEntities = dto.getShelves().stream()
          .map(shelfId -> shelvesRepository.findById(shelfId).orElse(null))
          .filter(Objects::nonNull)
          .collect(Collectors.toList());

  return BooksEntity.builder()
          .isbn(dto.getIsbn())
          .title(dto.getTitle())
          .author(dto.getAuthor())
          .pages(dto.getPages())
          .thumbnailUrl(dto.getThumbnailUrl())
          .selfLink(dto.getSelfLink())
          .shelf(shelvesEntities)
          .build();
 }

 private BooksDTO entityToDto(BooksEntity entity) {
  List<Long> shelfIds = entity.getShelf().stream()
          .map(ShelvesEntity::getId)
          .collect(Collectors.toList());

  return BooksDTO.builder()
          .isbn(entity.getIsbn())
          .title(entity.getTitle())
          .author(entity.getAuthor())
          .pages(entity.getPages())
          .thumbnailUrl(entity.getThumbnailUrl())
          .selfLink(entity.getSelfLink())
          .shelves(shelfIds)
          .build();
 }


 private List<BooksDTO> convertEntityListToDtoList(List<BooksEntity> entityList) {
  return entityList.stream()
          .map(this::entityToDto)
          .collect(Collectors.toList());
 }
}
