/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

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
 public BooksDTO createBook(BooksDTO booksDTO) {
  BooksEntity bookEntity = convertDtoToEntity(booksDTO);
  BooksEntity savedEntity = booksRepository.save(bookEntity);
  return convertEntityToDto(savedEntity);
 }

 @Override
 public BooksDTO getBookByIsbn(String isbn) {
  BooksEntity entity = booksRepository.findById(isbn).orElse(null);
  if (entity != null) {
   return convertEntityToDto(entity);
  }
  return null;
 }

 @Override
 public List<BooksDTO> getAllBooks() {
  List<BooksEntity> entityList = (List<BooksEntity>) booksRepository.findAll();
  return convertEntityListToDtoList(entityList);
 }

 @Override
 public BooksDTO updateBook(String isbn, BooksDTO updatedBooksDTO) {
  if (booksRepository.existsById(isbn)) {
   BooksEntity updatedEntity = convertDtoToEntity(updatedBooksDTO);
   updatedEntity.setIsbn(isbn); // Update the ID

   BooksEntity savedEntity = booksRepository.save(updatedEntity);
   return convertEntityToDto(savedEntity);
  }
  return null;
 }

 @Override
 public boolean deleteBook(String isbn) {
  booksRepository.deleteById(isbn);
  return false;
 }

 private BooksEntity convertDtoToEntity(BooksDTO dto) {
  return BooksEntity.builder()
          .isbn(dto.getIsbn())
          .title(dto.getTitle())
          .author(dto.getAuthor())
          .pages(dto.getPages())
          .thumbnailUrl(dto.getThumbnailUrl())
          .build();
 }

 private BooksDTO convertEntityToDto(BooksEntity entity) {
  return BooksDTO.builder()
          .isbn(entity.getIsbn())
          .title(entity.getTitle())
          .author(entity.getAuthor())
          .pages(entity.getPages())
          .thumbnailUrl(entity.getThumbnailUrl())
          .build();
 }

 private List<BooksDTO> convertEntityListToDtoList(List<BooksEntity> entityList) {
  return entityList.stream()
          .map(this::convertEntityToDto)
          .collect(Collectors.toList());
 }
}
