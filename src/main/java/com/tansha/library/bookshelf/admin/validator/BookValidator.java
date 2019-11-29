package com.tansha.library.bookshelf.admin.validator;

import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
    		   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
    		 String ID_PATTERN = "[0-9]+";  
    		 String STRING_PATTERN = "[a-zA-Z]+";  
    		 String MOBILE_PATTERN = "[0-9]{10}";  
    		 
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
       
    	Book book = (Book) o;
       
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookTitle", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbncode", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "publishedYear", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "longTitle", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noofPages", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "binding", "NotEmpty");
       
        if(book.getAuthorID() == -1 ) {
        	errors.rejectValue("authorID", "select.notEmpty");
        }
       

        if(book.getPublisherID() == -1 ) {
        	errors.rejectValue("publisherID", "select.notEmpty");
        }

        if(book.getLanguageId() == -1 ) {
        	errors.rejectValue("languageId", "select.notEmpty");
        }

        if(book.getCategoryID() == -1 ) {
        	errors.rejectValue("categoryID", "select.notEmpty");
        }

        if(book.getReadingLevelId() == -1 ) {
        	errors.rejectValue("readingLevelId", "select.notEmpty");
        }
 
        
        
        
        
    }
}
