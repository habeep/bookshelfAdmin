package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.BookCategory;
import com.tansha.library.bookshelf.admin.model.BookCategory;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.service.BookCategoryService;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	@Override
	public BookCategory getBookCategoryById(int booksCategoryId) {
		BookCategory obj = bookCategoryRepository.findByCategoryId(booksCategoryId);
		return obj;
	}	
	@Override
	public List<BookCategory> getAllBookCategories(){
		
		List<BookCategory> list = new ArrayList<>();
		bookCategoryRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addBookCategory(BookCategory bookCategory){
			
	        BookCategory list = bookCategoryRepository.findBycategoryName((bookCategory.getCategoryName())); 	
                if (list != null) {
    	           return false;
                } else {
    	        bookCategoryRepository.save(bookCategory);
    	        return true;
       }
	}
	@Override
	public void updateBookCategory(BookCategory bookCategory) {
		bookCategoryRepository.save(bookCategory);
	}
	@Override
	public void deleteBookCategory(int booksCategoryId) {
		bookCategoryRepository.delete(getBookCategoryById(booksCategoryId));
	}
	
	@Override
	public List<BookCategory> getHeaderCategories(){
		
		return bookCategoryRepository.getHeaderCategories(1);
	}
	@Override
	public Integer getBookCategoryByNameQuery(String bookCategoryName) {
		// TODO Auto-generated method stub
		Integer bookCategoryNameId=0;
		if(bookCategoryRepository.findByCategoryNameQuery(bookCategoryName) == null ) {
			BookCategory bookCategoryNameObj = new BookCategory();
			bookCategoryNameObj.setCategoryName(bookCategoryName);
			bookCategoryNameObj.setDescription(bookCategoryName);
			bookCategoryNameObj.setIsActive(1);
			bookCategoryRepository.save(bookCategoryNameObj);
			
			bookCategoryNameId = bookCategoryRepository.findByCategoryNameQuery(bookCategoryName);
		} else {
			bookCategoryNameId = bookCategoryRepository.findByCategoryNameQuery(bookCategoryName);
		}
		// TODO Auto-generated method stub
		return bookCategoryNameId;
	}
}
