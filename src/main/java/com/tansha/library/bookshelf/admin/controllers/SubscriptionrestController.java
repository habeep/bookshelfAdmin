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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.Subscription;
import com.tansha.library.bookshelf.admin.model.UserSubscription;
import com.tansha.library.bookshelf.admin.repository.UserSubscriptionRepository;
import com.tansha.library.bookshelf.admin.service.impl.SubscriptionServiceImpl;
import com.tansha.library.bookshelf.admin.service.SubscriptionService;


@RestController
public class SubscriptionrestController {
	
	@Autowired
	private SubscriptionServiceImpl subscriptionService;
		
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@RequestMapping(value = "/subscriptions", method = RequestMethod.GET)
	public List<Subscription> getSubscriptions(){
		
		List<Subscription> subscriptionrules = subscriptionService.getAllSubscriptions();
		return subscriptionrules;
		}
	
	@RequestMapping(value = "/activateusersubscriptions", method = RequestMethod.GET)
	public List<Object[]> getUnPaidSubscriptions(){
		System.out.println("activateusersubscriptions rest 1");
		List<Object[]> usersubscriptions = subscriptionService.getAllRequestedUserSubscriptions();
		System.out.println("activateusersubscriptions rest 2");
		return usersubscriptions;
	}
	
	@RequestMapping(value = "/activateusersubscription/confirmCashPayment", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> confirmPickup(@RequestParam("uscId") int uscId) {
		//int value=1;
		UserSubscription usc = userSubscriptionRepository.findById(uscId);
		if(usc!=null) {
			usc.setPaymentStatus("success");
			userSubscriptionRepository.save(usc);
		}
		return new ResponseEntity(usc, HttpStatus.OK);
	}
	
}