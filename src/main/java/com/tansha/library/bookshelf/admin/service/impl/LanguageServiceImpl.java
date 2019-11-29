package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Language;
import com.tansha.library.bookshelf.admin.model.Language;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.service.LanguageService;

@Service
public class LanguageServiceImpl implements LanguageService {
	@Autowired
	private LanguageRepository languageRepository;
	@Override
	public Language getLanguageById(int languageId) {
		Language obj = languageRepository.findByLanguageID(languageId);
		return obj;
	}	
	@Override
	public List<Language> getAllLanguages(){
		
		List<Language> list = new ArrayList<>();
		languageRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addLanguage(Language language){

	        Language list = languageRepository.findByLanguage(language.getLanguage()); 	
                if (list != null) {
    	           return false;
                } else {
    	        languageRepository.save(language);
    	        return true;
       }
	}
	@Override
	public void updateLanguage(Language language) {
		languageRepository.save(language);
	}
	@Override
	public void deleteLanguage(int languageId) {
		languageRepository.delete(getLanguageById(languageId));
	}
	@Override
	public Integer getLanguageByNameQuery(String language) {
		Integer languageId=0;
		if(languageRepository.findByLanguageQuery(language) == null ) {
			Language languageObj = new Language();
			languageObj.setLanguage(language);
			languageObj.setIsActive(1);
			languageRepository.save(languageObj);
			languageId = languageRepository.findByLanguageQuery(language);
		} else {
			languageId = languageRepository.findByLanguageQuery(language);
		}
		// TODO Auto-generated method stub
		return languageId;
	}
}
