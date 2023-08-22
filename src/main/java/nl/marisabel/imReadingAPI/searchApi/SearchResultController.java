/*
 *
 *  * imReading API
 *  * Copyright (c) 2023 Marisabel Munoz
 *  * This project is licensed under the terms of the MIT License.
 *  * For more information, please see the https://opensource.org/license/mit/.
 *
 *
 */

package nl.marisabel.imReadingAPI.searchApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
@RestController
public class SearchResultController {

 @Autowired
 GoogleBooksApiService apiService;



 @GetMapping(value = "/search")
 public ResponseEntity<List<BookDto>> getSearchResults(@RequestParam String query) throws IOException {

  String url = apiService.getApiUri(query);
  String jsonResult = String.valueOf(apiService.getBookDetailsFromResponse(url));

  ObjectMapper objectMapper = new ObjectMapper();
  JsonNode rootNode = objectMapper.readTree(jsonResult);

  List<BookDto> books = new ArrayList<>();

  JsonNode itemsNode = rootNode.path("items");

  if (itemsNode.isArray()) {
   for (JsonNode itemNode : itemsNode) {

    JsonNode volumeInfoNode = itemNode.path("volumeInfo");
    String title = volumeInfoNode.path("title").asText("Unknown Title");
    String author = volumeInfoNode.path("authors").path(0).asText("Unknown Author");
    String identifier = getIdentifier(volumeInfoNode.path("industryIdentifiers"));
    String thumbnail = volumeInfoNode.path("imageLinks").path("thumbnail").asText("No Cover");
    int pageCount = volumeInfoNode.path("pageCount").asInt(0);
    String selfLink = itemNode.path("selfLink").asText();


    BookDto bookDto = new BookDto();
    bookDto.setTitle(title);
    bookDto.setAuthor(author);
    bookDto.setIsbn(identifier);
    bookDto.setThumbnail(thumbnail);
    bookDto.setPageCount(pageCount);
    bookDto.setSelfLink(selfLink);

    books.add(bookDto);
   }
  }

  return ResponseEntity.ok().body(books);
 }

 private String getIdentifier(JsonNode identifiersNode) {
  for (JsonNode identifierNode : identifiersNode) {
   String type = identifierNode.path("type").asText();
   if (type.equals("ISBN_13")) {
    return identifierNode.path("identifier").asText();
   }
  }
  return "Unknown Identifier";
 }


}