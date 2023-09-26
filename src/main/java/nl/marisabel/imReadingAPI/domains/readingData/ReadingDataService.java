/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;

import org.springframework.stereotype.Service;

@Service
public interface ReadingDataService {

 ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingDataDTO);

 ReadingDataDTO getAllReadingDataForABook(String isbn);

 ReadingDataDTO updateReadingData(String isbn, ReadingDataDTO updatedReadingDataDTO);

 boolean deleteReadingData(String isbn);

 ReadingDataDTO entityToDto(ReadingDataEntity entity);

 ReadingDataEntity dtoToEntity(ReadingDataDTO dto);
}





