package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Language;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface LanguageRepository extends CrudRepository<Language,String> {

	Language findByLanguage(String language);
	Language findByLanguageID(int languageID);
	List<Language> findByIsActive(int isActive);
	
	@Query("SELECT language.languageID from Language language where language.language LIKE  %?1%")
	List<Integer> findByLanguageLike(String language);
	
	@Query("SELECT language.languageID from Language language where language.language=?1")
	Integer findByLanguageQuery(String language);
	 
}
