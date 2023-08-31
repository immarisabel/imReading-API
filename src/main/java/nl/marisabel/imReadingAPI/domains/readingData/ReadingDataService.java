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

 public ReadingDataDTO addReadingDataToBook(ReadingDataDTO readingDataDTO);

 ReadingDataDTO getReadingDataById(Long id);

 ReadingDataDTO updateReadingData(Long id, ReadingDataDTO updatedReadingDataDTO);

 boolean eraseReadingData(Long id);


}





