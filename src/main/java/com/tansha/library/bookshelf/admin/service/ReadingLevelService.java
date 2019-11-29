package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.ReadingLevel;

public interface ReadingLevelService {
	List<ReadingLevel> getAllReadingLevels();
	ReadingLevel getReadingLevelById(int bookCategoryId);
    boolean addReadingLevel(ReadingLevel bookCategory);
    void updateReadingLevel(ReadingLevel bookCategory);
    void deleteReadingLevel(int bookCategoryId);
    Integer getReadingLevelQuery(String readingLevelName);
    
}
