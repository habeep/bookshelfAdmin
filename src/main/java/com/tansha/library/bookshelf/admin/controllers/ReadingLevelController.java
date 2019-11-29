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

import com.tansha.library.bookshelf.admin.model.ReadingLevel;
import com.tansha.library.bookshelf.admin.model.ReadingLevel;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;
import com.tansha.library.bookshelf.admin.service.ReadingLevelService;

@Controller
public class ReadingLevelController {
	
	@Autowired
	private ReadingLevelService readinglevelService;
	
	@Autowired
	private ReadingLevelRepository readinglevelRepository;
	
	@RequestMapping(value = { "/managereadinglevel" }, method = RequestMethod.GET)
	public ModelAndView getAllReadingLevels() {
		ModelAndView modelAndView = new ModelAndView();
		List<ReadingLevel> list = readinglevelService.getAllReadingLevels();
		modelAndView.setViewName("managereadinglevel");
		modelAndView.addObject("readinglevel", list);
		return modelAndView;
	}

	@RequestMapping(value = { "/addreadinglevel" }, method = RequestMethod.GET)
	public ModelAndView addReadingLevel() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addreadinglevel");
		return modelAndView;
	}    
	

	@RequestMapping(value = { "/addreadinglevel" }, method = RequestMethod.POST)
	public ModelAndView addReadingLevel(@RequestParam("readinglevelName") final String readinglevelName,@RequestParam("description") final String description) {
		ModelAndView modelAndView = new ModelAndView();
		ReadingLevel readinglevel = new ReadingLevel();
		readinglevel.setReadingLevel(readinglevelName);
		readinglevel.setDescription(description);
		ReadingLevel isExist = readinglevelRepository.findByReadingLevel(readinglevelName);
		if (isExist == null) {
			readinglevelService.addReadingLevel(readinglevel);
			
			modelAndView.setViewName("redirect:/managereadinglevel");
		} else {
			modelAndView.addObject("errorMsg", "Reading Level is already available");
			modelAndView.setViewName("addreadinglevel");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updatereadinglevel/{readinglevelId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int readinglevelId) {

		ModelAndView modelandview = new ModelAndView();
		ReadingLevel readinglevel = readinglevelRepository.findByReadingLevelId(readinglevelId);
		if (readinglevel != null) {
			modelandview.addObject("readinglevelName", readinglevel.getReadingLevel());
			modelandview.addObject("description", readinglevel.getDescription());
			modelandview.addObject("readinglevelId", readinglevel.getReadingLevelId());
			modelandview.setViewName("updatereadinglevel");
		} else {
			modelandview.setViewName("redirect:/managereadinglevel");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updatereadinglevel", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("readinglevelId") final int readinglevelId,
			@RequestParam("readinglevelName") final String readinglevelName,@RequestParam("description") final String description) {

		ModelAndView modelandview = new ModelAndView();
		ReadingLevel readinglevel = readinglevelRepository.findByReadingLevelId(readinglevelId);
		ReadingLevel isExist = readinglevelRepository.findByReadingLevel(readinglevelName);
		if (readinglevel != null && isExist == null) {
			readinglevel.setReadingLevel(readinglevelName);
			readinglevel.setDescription(description);
			readinglevelRepository.save(readinglevel);

			modelandview.setViewName("redirect:/managereadinglevel");
		} else {
			modelandview.addObject("errorMsg", "Reading Level is already available");
			modelandview.addObject("readinglevelName", readinglevel.getReadingLevel());
			modelandview.addObject("description", readinglevel.getDescription());
			modelandview.addObject("readinglevelId", readinglevel.getReadingLevelId());
			modelandview.setViewName("updatereadinglevel");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deletereadinglevel/{readinglevelId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int readinglevelId, @PathVariable final int activeFlag) {
		
		ModelAndView modelandview = new ModelAndView();
		ReadingLevel readinglevel = readinglevelRepository.findByReadingLevelId(readinglevelId);
		if (readinglevel != null) {
			readinglevel.setIsActive(activeFlag);
			readinglevelRepository.save(readinglevel);
			modelandview.setViewName("redirect:/managereadinglevel");
		} else {
			modelandview.setViewName("redirect:/managereadinglevel");
		}
		return modelandview;
	}
	

}
