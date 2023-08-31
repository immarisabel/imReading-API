/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.googleSearchApi;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
@Log
public class GoogleBooksApiService {

 private RestTemplate restTemplate;
 private final String apiKey;

 public GoogleBooksApiService(RestTemplate restTemplate, @Value("${keys.gBooks}") String apiKey) {
  this.restTemplate = restTemplate;
  this.apiKey = apiKey;
 }


 // Generate url with ISBN and key
 public String getApiUri(String query) throws IOException {

  return "https://www.googleapis.com/books/v1/volumes?q=" + query + "&download=epub&key=" + apiKey + "&maxResults=20";

 }


 // deserialize json using record class
 public String getBookDetailsFromResponse(String url) {

  return restTemplate.getForObject(url, String.class);
 }


}