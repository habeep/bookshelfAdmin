package com.tansha.library.bookshelf.admin.service;

import java.util.List;

import com.tansha.library.bookshelf.admin.model.Book;

public interface BookService {
	List<Book> getAllBooks();
	Book getBookById(int bookId);
    boolean addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int bookId);
    List<Object[]> searchBooks(String searchStr);
    List<Object[]> selectSearchBooks(int readingLevelList, int categoryId,int languageId,int publisherId);
    List<Object[]> bookDetails(int bookId);
    
    List<Object[]> getFeaturedBooks();
   
    
}
