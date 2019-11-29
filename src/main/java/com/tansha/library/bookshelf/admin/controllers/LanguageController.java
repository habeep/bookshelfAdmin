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

import com.tansha.library.bookshelf.admin.model.Language;
import com.tansha.library.bookshelf.admin.model.Language;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.service.LanguageService;

@Controller
public class LanguageController {
	
	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@RequestMapping(value = { "/managelanguage" }, method = RequestMethod.GET)
	public ModelAndView getAllLanguages() {
		ModelAndView modelAndView = new ModelAndView();
		List<Language> list = languageService.getAllLanguages();
		modelAndView.setViewName("managelanguage");
		modelAndView.addObject("language", list);
		return modelAndView;
	}

	@RequestMapping(value = { "/addlanguage" }, method = RequestMethod.GET)
	public ModelAndView addLanguage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addlanguage");
		return modelAndView;
	}

	@RequestMapping(value = { "/addlanguage" }, method = RequestMethod.POST)
	public ModelAndView addLanguage(@RequestParam("language") final String languageName) {
		ModelAndView modelAndView = new ModelAndView();
		Language language = new Language();
		language.setLanguage(languageName);
		
		Language isExist = languageRepository.findByLanguage(languageName);
		if (isExist == null) {
			languageService.addLanguage(language);
			modelAndView.setViewName("redirect:/managelanguage");
		} else {
			modelAndView.addObject("errorMsg", "LanguageName is already available");
			modelAndView.setViewName("addlanguage");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/updatelanguage/{languageId}", method = RequestMethod.GET)
	public ModelAndView updateReligion(@PathVariable final int languageId) {

		ModelAndView modelandview = new ModelAndView();
		Language language = languageRepository.findByLanguageID(languageId);
		if (language != null) {
			modelandview.addObject("language", language.getLanguage());
			
			modelandview.addObject("languageId", language.getLanguageID());
			modelandview.setViewName("updatelanguage");
		} else {
			modelandview.setViewName("redirect:/managelanguage");
		}
		return modelandview;
	}

	@RequestMapping(value = "/updatelanguage", method = RequestMethod.POST)
	public ModelAndView updateReligion(@RequestParam("languageId") final int languageId,
			@RequestParam("language") final String languageName) {

		ModelAndView modelandview = new ModelAndView();
		Language language = languageRepository.findByLanguageID(languageId);
		Language isExist = languageRepository.findByLanguage(languageName);
		if (language != null && isExist == null) {
			language.setLanguage(languageName);
			
			languageRepository.save(language);

			modelandview.setViewName("redirect:/managelanguage");
		} else {
			modelandview.addObject("errorMsg", "LanguageName is already available");
			modelandview.addObject("language", language.getLanguage());
			
			modelandview.addObject("languageId", language.getLanguageID());
			modelandview.setViewName("updatelanguage");
		}
		return modelandview;
	}

	@RequestMapping(value = "/deletelanguage/{languageId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int languageId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Language language = languageRepository.findByLanguageID(languageId);
		if (language != null) {
			language.setIsActive(activeFlag);
			languageRepository.save(language);
			modelandview.setViewName("redirect:/managelanguage");
		} else {
			modelandview.setViewName("redirect:/managelanguage");
		}
		return modelandview;
	}

}
