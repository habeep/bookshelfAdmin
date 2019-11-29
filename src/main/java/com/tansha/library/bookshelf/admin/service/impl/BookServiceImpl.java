package com.tansha.library.bookshelf.admin.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tansha.library.bookshelf.admin.model.Author;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private LanguageRepository languageRepository;
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Override
	public Book getBookById(int bookId) {
		Book obj = bookRepository.findByBookId(bookId);
		return obj;
	}	
	@Override
	public List<Book> getAllBooks(){
		
		List<Book> list = new ArrayList<>();
		bookRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	@Override
	public synchronized boolean addBook(Book book){

	        List<Book> list = bookRepository.findByBookTitle(book.getBookTitle()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
                	book.setIsActive(1);
    	        bookRepository.save(book);
    	        return true;
       }
	}
	@Override
	public void updateBook(Book book) {
		
		bookRepository.save(book);
	}
	@Override
	public void deleteBook(int bookId) {
		bookRepository.delete(getBookById(bookId));
	}
	@Override
	public List<Object[]> getFeaturedBooks(){
		return bookRepository.getFeaturedBooks(1);
	}
	
	
		
	@Override
	public List<Object[]> searchBooks(String searchStr) {
		// TODO Auto-generated method stub
		
		
		List<Object[]> list = bookRepository.searchBooks(searchStr);
		//bookRepository.findByBookTitleOrIsbncode(searchStr, searchStr).forEach(e -> list.add(e));
		//bookRepository.saveAll(entities)(searchStr);
		return list;
	}
	
	@Override
	public List<Object[]> selectSearchBooks(int readingLevelList, int categoryId,int languageId,int publisherId) {
		// TODO Auto-generated method stub
		
		
		List<Object[]> list = bookRepository.selectSearchBooks(publisherId,categoryId,languageId,readingLevelList);
		//bookRepository.findByBookTitleOrIsbncode(searchStr, searchStr).forEach(e -> list.add(e));
		//bookRepository.saveAll(entities)(searchStr);
		return list;
	}
	@Override
	public List<Object[]> bookDetails(int bookId) {
		// TODO Auto-generated method stub
		
		
		List<Object[]> list = bookRepository.bookDetails(bookId);
		//bookRepository.findByBookTitleOrIsbncode(searchStr, searchStr).forEach(e -> list.add(e));
		//bookRepository.saveAll(entities)(searchStr);
		return list;
	}
}
