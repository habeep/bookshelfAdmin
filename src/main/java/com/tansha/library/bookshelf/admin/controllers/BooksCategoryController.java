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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.BookCategory;
import com.tansha.library.bookshelf.admin.model.BookCategory;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.service.BookCategoryService;

@Controller
public class BooksCategoryController {
	
	@Autowired
	private BookCategoryService bookCategoryService;
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	@RequestMapping(value = { "/managebookcategory" }, method = RequestMethod.GET)
	public ModelAndView getAllBookCategorys() {
		ModelAndView modelAndView = new ModelAndView();
		List<BookCategory> list = bookCategoryService.getAllBookCategories();
		modelAndView.setViewName("managebookcategory");
		modelAndView.addObject("bookCategory", list);
		return modelAndView;
	}

	@RequestMapping(value = { "/addbookcategory" }, method = RequestMethod.GET)
	public ModelAndView addBookCategory() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addbookcategory");
		return modelAndView;
	}

	@RequestMapping(value = { "/addbookcategory" }, method = RequestMethod.POST)
	public ModelAndView addBookCategory(@RequestParam("bookCategoryName") final String bookCategoryName,@RequestParam("description") final String description) {
		ModelAndView modelAndView = new ModelAndView();
		BookCategory bookCategory = new BookCategory();
		bookCategory.setCategoryName(bookCategoryName);
		bookCategory.setDescription(description);
		BookCategory isExist = bookCategoryRepository.findBycategoryName(bookCategoryName);
		if (isExist == null) {
			bookCategoryService.addBookCategory(bookCategory);
			modelAndView.setViewName("redirect:/managebookcategory");
		} else {
			modelAndView.addObject("errorMsg", "Book Category is already available");
			modelAndView.setViewName("addbookcategory");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updatebookcategory/{bookCategoryId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int bookCategoryId) {

		ModelAndView modelandview = new ModelAndView();
		BookCategory bookCategory = bookCategoryRepository.findByCategoryId(bookCategoryId);
		if (bookCategory != null) {
			modelandview.addObject("bookCategoryName", bookCategory.getCategoryName());
			modelandview.addObject("description", bookCategory.getDescription());
			modelandview.addObject("bookCategoryId", bookCategory.getCategoryId());
			modelandview.setViewName("updatebookcategory");
		} else {
			modelandview.setViewName("redirect:/managebookcategory");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updatebookcategory", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("bookCategoryId") final int bookCategoryId,
			@RequestParam("bookCategoryName") final String bookCategoryName,@RequestParam("description") final String description) {

		ModelAndView modelandview = new ModelAndView();
		BookCategory bookCategory = bookCategoryRepository.findByCategoryId(bookCategoryId);
		BookCategory isExist = bookCategoryRepository.findBycategoryName(bookCategoryName);
		if (bookCategory != null && isExist == null) {
			bookCategory.setCategoryName(bookCategoryName);
			bookCategory.setDescription(description);
			bookCategoryRepository.save(bookCategory);

			modelandview.setViewName("redirect:/managebookcategory");
		} else {
			modelandview.addObject("errorMsg", "Book Category is already available");
			modelandview.addObject("bookCategoryName", bookCategory.getCategoryName());
			modelandview.addObject("description", bookCategory.getDescription());
			modelandview.addObject("bookCategoryId", bookCategory.getCategoryId());
			modelandview.setViewName("updatebookcategory");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deletebookcategory/{bookCategoryId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int bookCategoryId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		BookCategory bookCategory = bookCategoryRepository.findByCategoryId(bookCategoryId);
		if (bookCategory != null) {
			bookCategory.setIsActive(activeFlag);
			bookCategoryRepository.save(bookCategory);
			modelandview.setViewName("redirect:/managebookcategory");
		} else {
			modelandview.setViewName("redirect:/managebookcategory");
		}
		return modelandview;
	}
	

}
