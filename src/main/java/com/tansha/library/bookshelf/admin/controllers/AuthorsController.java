package com.tansha.library.bookshelf.admin.controllers;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;


import com.tansha.library.bookshelf.admin.model.Author;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;

import com.tansha.library.bookshelf.admin.service.AuthorService;

@Controller
public class AuthorsController {
	
	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@RequestMapping(value = { "/manageauthor" }, method = RequestMethod.GET)
	public ModelAndView getAllAuthors() {
		ModelAndView modelAndView = new ModelAndView();
		List<Author> list = authorService.getAllAuthors();
		modelAndView.setViewName("manageauthor");
		modelAndView.addObject("author", list);
		return modelAndView;
	}

	@RequestMapping(value = { "/addauthor" }, method = RequestMethod.GET)
	public ModelAndView addAuthor() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addauthor");
		return modelAndView;
	}

	@RequestMapping(value = { "/addauthor" }, method = RequestMethod.POST)
	public ModelAndView addAuthor(@RequestParam("author") final String authorName) {
		ModelAndView modelAndView = new ModelAndView();
		Author author = new Author();
		author.setAuthorName(authorName);
		
		Author isExist = authorRepository.findByAuthorName(authorName);
		
		if (isExist == null) {
			authorService.addAuthor(author);
			modelAndView.setViewName("redirect:/manageauthor");
		} else {
			modelAndView.addObject("errorMsg", "AuthorName is already available");
			modelAndView.setViewName("addauthor");
		}

		return modelAndView;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/updateauthor/{authorId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int authorId) {

		ModelAndView modelandview = new ModelAndView();
		Author author = authorRepository.findByAuthorID(authorId);
		if (author != null) {
			modelandview.addObject("author", author.getAuthorName());
			
			modelandview.addObject("authorId", author.getAuthorID());
			modelandview.setViewName("updateauthor");
		} else {
			modelandview.setViewName("redirect:/manageauthor");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updateauthor", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("authorId") final int authorId,
			@RequestParam("author") final String authorName) {

		ModelAndView modelandview = new ModelAndView();
		Author author = authorRepository.findByAuthorID(authorId);
		Author isExist = authorRepository.findByAuthorName(authorName);
		if (author != null && isExist == null) {
			author.setAuthorName(authorName);
			
			authorRepository.save(author);

			modelandview.setViewName("redirect:/manageauthor");
		} else {
			modelandview.addObject("errorMsg", "AuthorName is already available");
			modelandview.addObject("author", author.getAuthorName());
			
			modelandview.addObject("authorId", author.getAuthorID());
			modelandview.setViewName("updateauthor");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deleteauthor/{authorId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int authorId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Author author = authorRepository.findByAuthorID(authorId);
		if (author != null) {
			author.setIsActive(activeFlag);
			authorRepository.save(author);
			modelandview.setViewName("redirect:/manageauthor");
		} else {
			modelandview.setViewName("redirect:/manageauthor");
		}
		return modelandview;
	}

}
