package com.tansha.library.bookshelf.admin.controllers;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRuleRepository;
import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.service.SubscriptionRuleService;
import com.tansha.library.bookshelf.admin.validator.SubscriptionRuleValidator;
import com.tansha.library.bookshelf.admin.validator.SubscriptionRuleValidator;

@Controller
public class SubscriptionRuleController {
	
	@Autowired
	private SubscriptionRuleService subscriptionRuleService;
	@Autowired
    private SubscriptionRuleValidator subscriptionruleValidator;
	@Autowired
	private SubscriptionRuleRepository subscriptionruleRepository;
	
	@RequestMapping(value = { "/managesubscriptionrule" }, method = RequestMethod.GET)
	public ModelAndView getAllSubscriptionRules() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("managesubscriptionrule");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/addsubscriptionrule" }, method = RequestMethod.GET)
	public ModelAndView addSubscriptionRule() {
		ModelAndView modelAndView = new ModelAndView();
		SubscriptionRule subscriptionrule = new SubscriptionRule();
		
		modelAndView.addObject("subscriptionrule", subscriptionrule);
		modelAndView.setViewName("addsubscriptionrule");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/addsubscriptionrule" }, method = RequestMethod.POST)
	public ModelAndView addSubscriptionRule(@Valid SubscriptionRule subscriptionrule,BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		
		subscriptionruleValidator.validate(subscriptionrule, bindingResult);
		
		
		
		
		if(bindingResult.hasErrors()) {
			
			
			modelAndView.setViewName("addsubscriptionrule");
		} else {
			subscriptionRuleService.addSubscriptionRule(subscriptionrule);
			modelAndView.setViewName("redirect:/managesubscriptionrule");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = { "/updatesubscriptionrule/{subscriptionruleId}" }, method = RequestMethod.GET)
	public ModelAndView updateSubscriptionRule(@PathVariable final int subscriptionruleId) {
		ModelAndView modelAndView = new ModelAndView();
		SubscriptionRule subscriptionrule = subscriptionruleRepository.findByRuleId(subscriptionruleId).get(0);
		modelAndView.addObject("subscriptionrule", subscriptionrule );
		modelAndView.setViewName("updatesubscriptionrule");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/updatesubscriptionrule" }, method = RequestMethod.POST)
	public ModelAndView updateSubscriptionRule(@Valid SubscriptionRule subscriptionrule,BindingResult bindingResult, final HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("subscription rule id"+subscriptionrule.getRuleId());
		System.out.println("subscription rule id"+subscriptionrule.getMaxNumberofBooks());
		System.out.println("subscription rule id"+subscriptionrule.getMaxNumberofDelivery());
		System.out.println("subscription rule id"+subscriptionrule.getNoofMonths());
		System.out.println("subscription rule id"+subscriptionrule.getRuleName());
		System.out.println("subscription rule id"+subscriptionrule.getIsActive());
		System.out.println("subscription rule id"+subscriptionrule.getCreatedAt());
		subscriptionruleValidator.validate(subscriptionrule, bindingResult);
		
		if(bindingResult.hasErrors()) {
			
			modelAndView.setViewName("updatesubscriptionrule");
		} else {
			subscriptionRuleService.updateSubscriptionRule(subscriptionrule);
			modelAndView.setViewName("redirect:/managesubscriptionrule");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/deletesubscriptionrule/{subscriptionruleId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int subscriptionruleId, @PathVariable final int activeFlag) {
		
		ModelAndView modelandview = new ModelAndView();
		SubscriptionRule subscriptionrule = subscriptionruleRepository.findByRuleId(subscriptionruleId).get(0);
		if (subscriptionrule != null) {
			subscriptionrule.setIsActive(activeFlag);
			subscriptionruleRepository.save(subscriptionrule);
			modelandview.setViewName("redirect:/managesubscriptionrule");
		} else {
			modelandview.setViewName("redirect:/managesubscriptionrule");
		}
		return modelandview;
	}
	

}
