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
import org.springframework.web.util.UriComponentsBuilder;

import com.tansha.library.bookshelf.admin.model.Publisher;
import com.tansha.library.bookshelf.admin.model.Publisher;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.service.PublisherService;

@Controller

public class PublishersController {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@RequestMapping(value = { "/managepublisher" }, method = RequestMethod.GET)
	public ModelAndView getAllPublishers() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("managepublisher");
		
		return modelAndView;
	}

	@RequestMapping(value = { "/addpublisher" }, method = RequestMethod.GET)
	public ModelAndView addPublisher() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addpublisher");
		return modelAndView;
	}

	@RequestMapping(value = { "/addpublisher" }, method = RequestMethod.POST)
	public ModelAndView addPublisher(@RequestParam("publisher") final String publisherName) {
		ModelAndView modelAndView = new ModelAndView();
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		
		Publisher isExist = publisherRepository.findByPublisherName(publisherName);
		if (isExist == null) {
			publisherService.addPublisher(publisher);
			modelAndView.setViewName("redirect:/managepublisher");
		} else {
			modelAndView.addObject("errorMsg", "PublisherName is already available");
			modelAndView.setViewName("addpublisher");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updatepublisher/{publisherId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int publisherId) {

		ModelAndView modelandview = new ModelAndView();
		Publisher publisher = publisherRepository.findByPublisherId(publisherId);
		if (publisher != null) {
			modelandview.addObject("publisher", publisher.getPublisherName());
			
			modelandview.addObject("publisherId", publisher.getPublisherId());
			modelandview.setViewName("updatepublisher");
		} else {
			modelandview.setViewName("redirect:/managepublisher");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updatepublisher", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("publisherId") final int publisherId,
			@RequestParam("publisher") final String publisherName) {

		ModelAndView modelandview = new ModelAndView();
		Publisher publisher = publisherRepository.findByPublisherId(publisherId);
		Publisher isExist = publisherRepository.findByPublisherName(publisherName);
		if (publisher != null && isExist == null) {
			publisher.setPublisherName(publisherName);
			
			publisherRepository.save(publisher);

			modelandview.setViewName("redirect:/managepublisher");
		} else {
			modelandview.addObject("errorMsg", "PublisherName is already available");
			modelandview.addObject("publisher", publisher.getPublisherName());
			
			modelandview.addObject("publisherId", publisher.getPublisherId());
			modelandview.setViewName("updatepublisher");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deletepublisher/{publisherId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int publisherId, @PathVariable final int activeFlag) {
		
		ModelAndView modelandview = new ModelAndView();
		Publisher publisher = publisherRepository.findByPublisherId(publisherId);
		if (publisher != null) {
			publisher.setIsActive(activeFlag);
			publisherRepository.save(publisher);
			modelandview.setViewName("redirect:/managepublisher");
		} else {
			modelandview.setViewName("redirect:/managepublisher");
		}
		return modelandview;
	}

}
