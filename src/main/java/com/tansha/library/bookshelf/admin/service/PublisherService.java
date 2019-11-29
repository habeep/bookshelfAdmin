package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Publisher;

public interface PublisherService {
	List<Publisher> getAllPublishers();
	Publisher getPublisherById(int publisherId);
    boolean addPublisher(Publisher publisher);
    void updatePublisher(Publisher publisher);
    void deletePublisher(int publisherId);
    Integer getPublisherByNameQuery(String publisherName);
}
