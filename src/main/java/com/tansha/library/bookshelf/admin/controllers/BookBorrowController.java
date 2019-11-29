package com.tansha.library.bookshelf.admin.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;

@Controller
public class BookBorrowController {
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;

	@RequestMapping(value = { "/myupcomingdeliveries" }, method = RequestMethod.GET)
	public ModelAndView myUpcomingDelivery() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
		modelAndView.setViewName("myupcomingdeliveries");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/adminupcomingdeliveries" }, method = RequestMethod.GET)
	public ModelAndView adminUpcomingDelivery() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
		modelAndView.setViewName("adminupcomingdeliveries");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/mycompleteddeliveries" }, method = RequestMethod.GET)
	public ModelAndView myCompleteDelivery() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
		modelAndView.setViewName("mycompleteddeliveries");
		return modelAndView;
	}
	@RequestMapping(value = { "/myupcomingpickups" }, method = RequestMethod.GET)
	public ModelAndView myUpcomingPickup() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
		modelAndView.setViewName("myupcomingpickups");
		return modelAndView;
	}
	@RequestMapping(value = { "/mycompletedpickups" }, method = RequestMethod.GET)
	public ModelAndView myCompletePickups() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
		modelAndView.setViewName("mycompletedpickups");
		return modelAndView;
	}
		
	
	@RequestMapping(value = { "/deliveryorderdetailsList/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView deliveryOrderDetailsList(@PathVariable final String orderId) {
		System.out.println("enter deliveryorderdetailsList ");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderId", orderId);
		modelAndView.setViewName("deliveryorderdetailsList");
		return modelAndView;
	}
	
	
	@RequestMapping(value = { "/admindeliveryorderdetailsList/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView adminDeliveryOrderDetailsList(@PathVariable final String orderId) {
		System.out.println("enter deliveryorderdetailsList ");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderId", orderId);
		modelAndView.setViewName("admindeliveryorderdetailsList");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/pickuporderdetailsList/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView pickupOrderDetailsList(@PathVariable final String orderId) {
		System.out.println("enter pickuporderdetailsList");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderId", orderId);
		modelAndView.setViewName("pickuporderdetailsList");
		return modelAndView;
	}

	@RequestMapping(value = "/updatemydeliveries/{bookBorrowId}/{type_return}", method = RequestMethod.GET)
	public ModelAndView returnSatus(@PathVariable final int bookBorrowId, @PathVariable final int type_return) {
		System.out.println("enter the book borrow update my deliveries");
		ModelAndView modelandview = new ModelAndView();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(bookBorrowId);
		System.out.println("the borrow id is"+bookBorrowId);
		if (bookBorrow != null) {
			bookBorrow.setType_return(type_return);
			booksBorrowRepository.save(bookBorrow);
			modelandview.setViewName("redirect:/mydeliveries");
		} else {
			modelandview.setViewName("redirect:/mydeliveries");
		}
		return modelandview;
	}
	
	@RequestMapping(value = "/updatemydeliveries/{bookBorrowId}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable final int bookBorrowId) {
		LocalDate currentDate = LocalDate.now();
		System.out.println("enter the book borrow");
		ModelAndView modelandview = new ModelAndView();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(bookBorrowId);
		System.out.println("bookborowwwwwwwwwwwwwwwwwwwww"+bookBorrow);
		if(bookBorrow != null) {
			modelandview.addObject("bookBorrow",bookBorrow);
			modelandview.setViewName("updatedelivery");
		} else {
			modelandview.setViewName("mydeliveries");
		}
		
		return modelandview;
	}
	
	@RequestMapping(value = { "/updatemydeliveries" }, method = RequestMethod.POST)
	public ModelAndView Mydelivery(@Valid BookBorrow bookBorrow) {
		System.out.println("enter update my deliveries");
		ModelAndView modelAndView = new ModelAndView();
	    	booksBorrowRepository.save(bookBorrow);
		modelAndView.addObject("bookBorrow", bookBorrow);
		modelAndView.setViewName("redirect:/mydeliveries");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/ordersdetails/{orderId}" }, method = RequestMethod.GET)
	public ModelAndView myOrederDetails(@PathVariable final String orderId) {
		System.out.println("enter update myorderdetails deliveries");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("orderId", orderId);
		modelAndView.setViewName("ordersdetails");
		return modelAndView;
	}
	
	

}
