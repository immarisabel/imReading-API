/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.reviews;

import org.springframework.stereotype.Service;

@Service
public interface ReviewsService {

 Long getReviewIdForBook(String isbn);

 ReviewsDTO addReviewToBook(ReviewsDTO reviewsDTO);

 ReviewsDTO getReviewForABook(String isbn);


 ReviewsDTO updateReview(String isbn, ReviewsDTO updatedReviewsDTO);

 boolean deleteReview(String isbn);

 ReviewsDTO entityToDto(ReviewsEntity entity);

 ReviewsEntity dtoToEntity(ReviewsDTO dto);

}





