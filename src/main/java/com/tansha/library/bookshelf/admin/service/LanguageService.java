package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Language;

public interface LanguageService {
	List<Language> getAllLanguages();
	Language getLanguageById(int languageId);
    boolean addLanguage(Language language);
    void updateLanguage(Language language);
    void deleteLanguage(int languageId);
    Integer getLanguageByNameQuery(String language);
}
