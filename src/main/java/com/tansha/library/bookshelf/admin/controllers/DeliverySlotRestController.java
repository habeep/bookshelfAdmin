package com.tansha.library.bookshelf.admin.controllers;


import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.DeliverySlot;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.DeliverySlotRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.service.impl.DeliverySlotServiceImpl;


@RestController
public class DeliverySlotRestController {
	
	@Autowired
	private DeliverySlotServiceImpl deliveryslotService;
	
	@Autowired
	private DeliverySlotRepository deliverySlotRepository;
	
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@RequestMapping(value = "/deliveryslots", method = RequestMethod.GET)
	public List<Object[]> getDeliverySlots(){
		List<Object[]> deliveryslots = deliverySlotRepository.getAllDeliveriesSlots();
		return deliveryslots;
		}
	
	@RequestMapping(value = "/mydeliveryslots", method = RequestMethod.GET)
	public List<Object[]> getMyDeliverySlots(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    
		List<Object[]> deliveryslots = deliverySlotRepository.getStaffDeliveriesSlots(staff.getId());
		return deliveryslots;
		}
	

	
	
//	@RequestMapping(value = "/deliverydate", method = RequestMethod.POST)
//	public ResponseEntity<BookBorrow> addRating(@RequestParam("borrowID") int borrowID,@RequestParam("deliveredOn") Date deliveredOn){
//		System.out.println("enter the delivery on details");
//		BookBorrow ratingObj = new BookBorrow();
//		ratingObj.setDeliveredOn(deliveredOn);
//		if(booksBorrowRepository.save(ratingObj) != null) {
//			System.out.println("CREATED");
//			return new ResponseEntity<BookBorrow>(ratingObj, HttpStatus.CREATED);
//		}
//		else {
//			System.out.println("CONFLICT");
//			return new ResponseEntity<BookBorrow>(ratingObj,HttpStatus.CONFLICT);
//		}	
//	}
//	
	
}
