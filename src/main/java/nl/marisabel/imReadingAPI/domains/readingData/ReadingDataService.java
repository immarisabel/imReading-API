/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.readingData;

import nl.marisabel.imReadingAPI.domains.logs.LogsDTO;
import nl.marisabel.imReadingAPI.domains.logs.LogsEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReadingDataService {

 ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingDataDTO);

 ReadingDataDTO getAllReadingDataForABook(String isbn);

 ReadingDataDTO updateReadingData(Long id, ReadingDataDTO updatedReadingDataDTO);

 boolean eraseReadingData(Long id);

 ReadingDataDTO entityToDto(ReadingDataEntity entity);

 ReadingDataEntity dtoToEntity(ReadingDataDTO dto);
}





