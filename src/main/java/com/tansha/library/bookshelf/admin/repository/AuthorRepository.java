package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Author;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
//import org.springframework.data;
//import org.springframework.boot.autoconfigur
@Transactional
public interface AuthorRepository extends CrudRepository<Author,String> {

	Author findByAuthorName(String author);
	List<Author> findByIsActive(int isActive);
	@Query("SELECT author.authorID from Author author where author.authorName LIKE  %?1%")
	List<Integer> findByAuthorNameLike(String author);
	
	Author findByAuthorID(int authorID);
	
	@Query("SELECT author.authorID from Author author where author.authorName = ?1 ")
	Integer findByAuthorNameQuery(String author);

	
	
	/*@Query("SELECT authors from Authors authors where authors.authorName LIKE  %?1%")
	List<Author> findByAuthor(String searchStr);*/
	
	 
}
