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
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.Author;
import com.tansha.library.bookshelf.admin.service.impl.AuthorServiceImpl;
import com.tansha.library.bookshelf.admin.service.impl.AuthorServiceImpl;


@RestController
public class AuthorRestController {
	
	@Autowired
	private AuthorServiceImpl authorService;
	
	/*@RequestMapping(value={ "/getauthorbyid"}, method = RequestMethod.GET)
	public ModelAndView getAuthorById(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Author author = authorService.getAuthorById(id);
		return modelAndView;
	} */
	
	@RequestMapping(value = "/authors", method = RequestMethod.GET)
	public List<Author> getAuthors(){
		
		List<Author> authors = authorService.getAllAuthors();
		return authors;
		}
	
	
}
