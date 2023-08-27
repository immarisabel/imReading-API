/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.shelves;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShelvesService {
 ShelvesDTO createShelf(ShelvesDTO shelf);

 ShelvesDTO getShelfById(Long id);

 List<ShelvesDTO> getAllShelves();

 ShelvesDTO updateShelf(Long id, ShelvesDTO updateShelf);

 boolean deleteShelf(Long id);
}

