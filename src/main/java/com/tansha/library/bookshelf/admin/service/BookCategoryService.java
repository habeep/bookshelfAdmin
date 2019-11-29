package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.BookCategory;

public interface BookCategoryService {
	List<BookCategory> getAllBookCategories();
	BookCategory getBookCategoryById(int bookCategoryId);
    boolean addBookCategory(BookCategory bookCategory);
    void updateBookCategory(BookCategory bookCategory);
    void deleteBookCategory(int bookCategoryId);
    List<BookCategory> getHeaderCategories(); 
    Integer getBookCategoryByNameQuery(String bookCategoryName);
}
