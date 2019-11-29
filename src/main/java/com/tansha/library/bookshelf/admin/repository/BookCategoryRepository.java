package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.BookCategory;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface BookCategoryRepository extends CrudRepository<BookCategory,String> {

	BookCategory findBycategoryName(String categoryName);
	BookCategory findByCategoryId(int categoryId);
	List<BookCategory> findByIsActive(int isActive);
	@Query("SELECT bookCategory  from BookCategory bookCategory where bookCategory.isHeader = ?1")
	List<BookCategory> getHeaderCategories(int isHeaderFlag);
	
	@Query("SELECT bookCategory.categoryId from BookCategory bookCategory where bookCategory.categoryName LIKE  %?1%")
	List<Integer> findByCategoryNameLike(String bookCategoryName);
	
	@Query("SELECT bookCategory.categoryId from BookCategory bookCategory where bookCategory.categoryName=?1")
	Integer findByCategoryNameQuery(String bookCategoryName);
	 
}
