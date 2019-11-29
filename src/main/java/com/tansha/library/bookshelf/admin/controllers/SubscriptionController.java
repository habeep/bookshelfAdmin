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

import com.tansha.library.bookshelf.admin.model.DeliverySlot;
import com.tansha.library.bookshelf.admin.model.Subscription;
import com.tansha.library.bookshelf.admin.repository.RoleRepository;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRepository;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRuleRepository;
import com.tansha.library.bookshelf.admin.service.SubscriptionService;
import com.tansha.library.bookshelf.admin.validator.SubscriptionValidator;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private SubscriptionRepository  subscriptionRepository;
	
	@Autowired
	private SubscriptionRuleRepository  subscriptionRuleRepository;
	
	@Autowired
	private SubscriptionValidator  subscriptionValidator;
	
	
	
	@RequestMapping(value = { "/managesubscription" }, method = RequestMethod.GET)
	public ModelAndView getAllDeliverySlots() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("managesubscription");

		return modelAndView;
	}


	@RequestMapping(value = { "/addsubscription" }, method = RequestMethod.GET)
	public ModelAndView addSubscription() {
		ModelAndView modelAndView = new ModelAndView();
		Subscription subscription = new Subscription();
		modelAndView.addObject("rules", subscriptionRuleRepository.findAll());
		modelAndView.addObject("subscription", subscription);
		modelAndView.setViewName("addsubscription");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/addsubscription" }, method = RequestMethod.POST)
	public ModelAndView addSubscription(@Valid Subscription subscription,BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		subscriptionValidator.validate(subscription, bindingResult);
		modelAndView.addObject("subscription", subscription);
		modelAndView.addObject("rules", subscriptionRuleRepository.findAll());
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("addsubscription");
		} else {
			subscriptionService.addSubscription(subscription);
			modelAndView.setViewName("redirect:/managesubscription");
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/updatesubscription/{subscriptionId}" }, method = RequestMethod.GET)
	public ModelAndView updateSubscription(@PathVariable final int subscriptionId) {
		ModelAndView modelAndView = new ModelAndView();
		Subscription subscription = subscriptionRepository.findBySubcId(subscriptionId).get(0);
		modelAndView.addObject("rules", subscriptionRuleRepository.findAll());		
		modelAndView.addObject("subscription", subscription);
		modelAndView.setViewName("updatesubscription");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/updatesubscription" }, method = RequestMethod.POST)
	public ModelAndView updateSubscription(@Valid Subscription subscription,BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		subscriptionValidator.validate(subscription, bindingResult);
		modelAndView.addObject("subscription", subscription);
		modelAndView.addObject("rules", subscriptionRuleRepository.findAll());		
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("updatesubscription");
		} else {
			System.out.println("Subsc >>> "+subscription.toString());
			subscriptionService.updateSubscription(subscription);
			modelAndView.setViewName("redirect:/managesubscription");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/deletesubscription/{subscriptionId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteSubscription(@PathVariable final int subscriptionId, @PathVariable final int activeFlag) {
		
		ModelAndView modelandview = new ModelAndView();
		Subscription subscription = subscriptionRepository.findBySubcId(subscriptionId).get(0);
		if (subscription != null) {
			
			subscription.setIsActive(activeFlag);
			subscriptionRepository.save(subscription);
			modelandview.setViewName("redirect:/managesubscription");
		} else {
			modelandview.setViewName("redirect:/managesubscription");
		}
		return modelandview;
	}

	/*
	 * User Subscription Controllers
	 */
	
	@RequestMapping(value = { "/activateusersubscription" }, method = RequestMethod.GET)
	public ModelAndView activateusersubscription() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("activateusersubscription");
		return modelAndView;
	}
	
}
