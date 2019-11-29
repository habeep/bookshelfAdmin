
package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Publisher;
import com.tansha.library.bookshelf.admin.model.Publisher;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {
	@Autowired
	private PublisherRepository publisherRepository;
	@Override
	public Publisher getPublisherById(int publisherId) {
		Publisher obj = publisherRepository.findByPublisherId(publisherId);
		return obj;
	}	
	@Override
	public List<Publisher> getAllPublishers(){
		
		List<Publisher> list = new ArrayList<>();
		publisherRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addPublisher(Publisher publisher){

	        Publisher list = publisherRepository.findByPublisherName(publisher.getPublisherName()); 	
                if (list != null) {
    	           return false;
                } else {
    	        publisherRepository.save(publisher);
    	        return true;
       }
	}
	@Override
	public void updatePublisher(Publisher publisher) {
		publisherRepository.save(publisher);
	}
	@Override
	public void deletePublisher(int publisherId) {
		publisherRepository.delete(getPublisherById(publisherId));
	}
	@Override
	public Integer getPublisherByNameQuery(String publisherName) {
		Integer publisherId=0;
		if(publisherRepository.findByPublisherNameQuery(publisherName) == null ) {
			Publisher publisherNameObj = new Publisher();
			publisherNameObj.setPublisherName(publisherName);
			publisherNameObj.setIsActive(1);
			publisherRepository.save(publisherNameObj);
			
			publisherId = publisherRepository.findByPublisherNameQuery(publisherName);
		} else {
			publisherId = publisherRepository.findByPublisherNameQuery(publisherName);
		}
		// TODO Auto-generated method stub
		return publisherId;
	}
}
