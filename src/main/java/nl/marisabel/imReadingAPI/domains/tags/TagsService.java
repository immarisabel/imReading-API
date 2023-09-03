/*
 *  imReading API
 * Copyright (c) 2023 Marisabel Munoz
 * This project is licensed under the terms of the MIT License.
 * For more information, please see the https://opensource.org/license/mit/.
 */

package nl.marisabel.imReadingAPI.domains.tags;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagsService {
 TagsDTO createTag(TagsDTO shelf);

 TagsDTO getTagById(Long id);

 List<TagsDTO> getAllTags();

 TagsDTO updateTag(Long id, TagsDTO updateShelf);

 boolean deleteTag(Long id);

 boolean isNameDuplicate(String tagName);

 TagsDTO getTagByName(String tagName);
}

