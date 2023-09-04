/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ImReadingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImReadingApiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}


// TODO : add a api body for books under a category : /books/category/{id}
// TODO : add the shelves body to the result of search book response
//  	in order to pass it on full to the client to insert books
//			{
//  "isbn": "string",
//  "title": "string",
//  "author": "string",
//  "pages": 0,
//  "thumbnailUrl": "string",
//  "selfLink": "string",
//  "shelves": [
//    0
//  ]
//}
