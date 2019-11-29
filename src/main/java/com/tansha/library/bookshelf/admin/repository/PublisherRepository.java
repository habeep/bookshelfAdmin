package com.tansha.library.bookshelf.admin.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.tansha.library.bookshelf.admin.model.Publisher;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
@Transactional
public interface PublisherRepository extends CrudRepository<Publisher,String> {

	Publisher findByPublisherName(String publisherName);
	Publisher findByPublisherId(int publisherId);
	List<Publisher> findByIsActive(int isActive);
	
	@Query("SELECT publisher.publisherId from Publisher publisher where publisher.publisherName LIKE  %?1%")
	List<Integer> findByPublisherNameLike(String publisherName);
	
	@Query("SELECT publisher.publisherId from Publisher publisher where publisher.publisherName = ?1 ")
	Integer findByPublisherNameQuery(String publisherName);
	 
}
