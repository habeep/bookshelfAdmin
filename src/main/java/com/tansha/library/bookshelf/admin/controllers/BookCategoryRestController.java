package com.tansha.library.bookshelf.admin.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.tansha.library.bookshelf.admin.model.BookCategory;
import com.tansha.library.bookshelf.admin.service.impl.BookCategoryServiceImpl;



@RestController
public class BookCategoryRestController {
	
	@Autowired
	private BookCategoryServiceImpl bookCategoryService;
	
	/*@RequestMapping(value={ "/getauthorbyid"}, method = RequestMethod.GET)
	public ModelAndView getBookCategoryById(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		BookCategory author = authorService.getBookCategoryById(id);
		return modelAndView;
	} */
	
	@RequestMapping(value = "/bookcategories", method = RequestMethod.GET)
	public List<BookCategory> getBookCategorys(){
		List<BookCategory> bookcategories = bookCategoryService.getAllBookCategories();
		return bookcategories;
		}
	
	
}
