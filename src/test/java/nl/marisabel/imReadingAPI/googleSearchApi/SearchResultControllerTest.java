/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.googleSearchApi;

import nl.marisabel.imReadingAPI.domains.googleSearchApi.GoogleBooksApiService;
import nl.marisabel.imReadingAPI.domains.googleSearchApi.SearchResultController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchResultControllerTest {

 @InjectMocks
 private SearchResultController controller;

 @Mock
 private GoogleBooksApiService apiService;

 private MockMvc mockMvc;

 @Before
 public void setUp() {
  mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
 }


 @Test
 public void testGetSearchResults() throws Exception {
  // Create a mock JSON response
  String mockJsonResult = "Your mock JSON response here";

  // Configure mock behavior
  when(apiService.getApiUri(anyString())).thenReturn("mockApiUrl");
  when(apiService.getBookDetailsFromResponse("mockApiUrl")).thenReturn(mockJsonResult);

  // Perform the request and assertions
  MvcResult result = mockMvc.perform(get("/search")
                  .param("query", "mockQuery"))
          .andExpect(status().isOk()) // Expect HTTP 200 OK
          .andExpect(jsonPath("$", hasSize(1))) // Assuming there's one book in your mock JSON
          .andExpect(jsonPath("$[0].title", is("Mock Title")))
          .andExpect(jsonPath("$[0].author", is("Mock Author")))
          .andReturn();

  // Optionally, you can also assert the response content
  String responseBody = result.getResponse().getContentAsString();
  assertThat(responseBody, containsString("Mock Title"));
  assertThat(responseBody, containsString("Mock Author"));
 }
}
