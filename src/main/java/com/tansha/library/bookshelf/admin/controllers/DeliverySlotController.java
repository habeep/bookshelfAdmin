package com.tansha.library.bookshelf.admin.controllers;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.DeliverySlot;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.AreaRepository;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.DeliverySlotRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.service.DeliverySlotService;
import com.tansha.library.bookshelf.admin.validator.DeliverySlotValidator;





@Controller
public class DeliverySlotController {
	
	@Autowired
	private DeliverySlotService deliverySlotService;
	
	@Autowired
	private DeliverySlotRepository deliverySlotRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private DeliverySlotValidator deliverySlotValidator;
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;
	
	@RequestMapping(value = { "/managedeliveryslot" }, method = RequestMethod.GET)
	public ModelAndView getAllDeliverySlots() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("managedeliveryslot");

		return modelAndView;
	}

	@RequestMapping(value = { "/mydeliveryslot" }, method = RequestMethod.GET)
	public ModelAndView getAllMyDeliverySlots() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("mydeliveryslots");

		return modelAndView;
	}
	
	@RequestMapping(value = { "/adddeliveryslot" }, method = RequestMethod.GET)
	public ModelAndView addDeliveryslots() {
		ModelAndView modelAndView = new ModelAndView();
		DeliverySlot deliveryslot = new DeliverySlot();
		
		modelAndView.addObject("areas", areaRepository.findAll());
		modelAndView.addObject("staffs", staffRepository.findAll());
		
		modelAndView.addObject("deliveryslot", deliveryslot);
		modelAndView.setViewName("adddeliveryslot");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/adddeliveryslot" }, method = RequestMethod.POST)
	public ModelAndView addDeliveryslot(@Valid @ModelAttribute("deliveryslot") DeliverySlot deliveryslot,BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		deliverySlotValidator.validate(deliveryslot, bindingResult);
		modelAndView.addObject("deliveryslot", deliveryslot);
		modelAndView.addObject("areas", areaRepository.findAll());
		modelAndView.addObject("staffs", staffRepository.findAll());
		modelAndView.addObject("localDate", LocalDate.now());
		/*for(ObjectError err:bindingResult.getAllErrors()) {
		}
		*/
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("adddeliveryslot");
		} else {
			deliverySlotService.addDeliverySlot(deliveryslot);
			modelAndView.setViewName("redirect:/managedeliveryslot");
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/updatedeliveryslot/{deliverySlotId}" }, method = RequestMethod.GET)
	public ModelAndView updateDeliveryslot(@PathVariable final int deliverySlotId) {
		ModelAndView modelAndView = new ModelAndView();
		
		DeliverySlot deliveryslot = deliverySlotRepository.findBySlotID(deliverySlotId);
		modelAndView.addObject("areas", areaRepository.findAll());
		modelAndView.addObject("staffs", staffRepository.findAll());
		
		modelAndView.addObject("deliveryslot", deliveryslot);
		modelAndView.setViewName("updatedeliveryslot");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/updatedeliveryslot" }, method = RequestMethod.POST)
	public ModelAndView updateDeliveryslot(@Valid DeliverySlot deliveryslot,BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		deliverySlotValidator.validate(deliveryslot, bindingResult);
		modelAndView.addObject("deliveryslot", deliveryslot);
		modelAndView.addObject("areas", areaRepository.findAll());
		modelAndView.addObject("staffs", staffRepository.findAll());
		
		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("updatedeliveryslot");
		} else {
			deliverySlotService.updateDeliverySlot(deliveryslot);
			modelAndView.setViewName("redirect:/managedeliveryslot");
		}
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/deletedeliveryslot/{deliverySlotId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteDeliveryslot(@PathVariable final int deliverySlotId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		DeliverySlot deliverySlot = deliverySlotRepository.findBySlotID(deliverySlotId);
		if (deliverySlot != null) {
			deliverySlot.setIsActive(activeFlag);
			deliverySlotRepository.save(deliverySlot);
			modelandview.setViewName("redirect:/managedeliveryslot");
		} else {
			modelandview.setViewName("redirect:/managedeliveryslot");
		}
		return modelandview;
	}
	
	 
	
	
	
		
	
}
