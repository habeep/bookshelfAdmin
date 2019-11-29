package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.ReadingLevel;
import com.tansha.library.bookshelf.admin.model.ReadingLevel;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;
import com.tansha.library.bookshelf.admin.service.ReadingLevelService;

@Service
public class ReadingLevelServiceImpl implements ReadingLevelService {
	@Autowired
	private ReadingLevelRepository readingLevelRepository;
	@Override
	public ReadingLevel getReadingLevelById(int readingLevelId) {
		ReadingLevel obj = readingLevelRepository.findByReadingLevelId(readingLevelId);
		return obj;
	}	
	@Override
	public List<ReadingLevel> getAllReadingLevels(){
		
		List<ReadingLevel> list = new ArrayList<>();
		readingLevelRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addReadingLevel(ReadingLevel readingLevel){
			
	        ReadingLevel list = readingLevelRepository.findByReadingLevel((readingLevel.getReadingLevel())); 	
                if (list != null) {
    	           return false;
                } else {
    	        readingLevelRepository.save(readingLevel);
    	        return true;
       }
	}
	@Override
	public void updateReadingLevel(ReadingLevel readingLevel) {
		readingLevelRepository.save(readingLevel);
	}
	@Override
	public void deleteReadingLevel(int readingLevelId) {
		readingLevelRepository.delete(getReadingLevelById(readingLevelId));
	}
	@Override
	public Integer getReadingLevelQuery(String readingLevelName) {
		Integer readingLevelId=0;
		if(readingLevelRepository.findByReadingLevelQuery(readingLevelName) == null ) {
			ReadingLevel readingLevelNameObj = new ReadingLevel();
			readingLevelNameObj.setReadingLevel(readingLevelName);
			readingLevelNameObj.setDescription(readingLevelName);
			readingLevelNameObj.setIsActive(1);
			readingLevelRepository.save(readingLevelNameObj);
			
			readingLevelId = readingLevelRepository.findByReadingLevelQuery(readingLevelName);
		} else {
			readingLevelId = readingLevelRepository.findByReadingLevelQuery(readingLevelName);
		}
		// TODO Auto-generated method stub
		return readingLevelId;
	}
	
	
}
