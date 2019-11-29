package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import com.tansha.library.bookshelf.admin.model.ReadingLevel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface ReadingLevelRepository extends CrudRepository<ReadingLevel,String> {

	ReadingLevel findByReadingLevel(String readingLevel);
	ReadingLevel findByReadingLevelId(int readingLevelId);
	List<ReadingLevel> findAll();
	List<ReadingLevel> findByIsActive(int isActive);
	/*@Query("SELECT bookCategory.categoryId from BookCategory bookCategory where bookCategory.categoryName LIKE  %?1%")
	List<Integer> findByCategoryNameLike(String bookCategoryName);*/
	
	@Query("SELECT readinglevel.readingLevelId from ReadingLevel readinglevel where readinglevel.readingLevel = ?1 ")
	Integer findByReadingLevelQuery(String readinglevelName);
	 
}
