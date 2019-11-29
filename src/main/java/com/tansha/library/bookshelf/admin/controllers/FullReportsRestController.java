package com.tansha.library.bookshelf.admin.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.PaymentConfirm;
import com.tansha.library.bookshelf.admin.model.Testimonial;
import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.UserSubscription;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.PaymentConfirmRepository;
import com.tansha.library.bookshelf.admin.repository.TestimonialRepository;
import com.tansha.library.bookshelf.admin.repository.UserRepository;
import com.tansha.library.bookshelf.admin.repository.UserSubscriptionRepository;

@RestController
public class FullReportsRestController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BooksBorrowRepository bookBorrowRepository;
	
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;
	
	
	@Autowired
	private PaymentConfirmRepository paymentConfirmRepository;
	
	@Autowired
	private TestimonialRepository testimonialRepository;
	
	
	@RequestMapping(value = "/stockbooks", method = RequestMethod.GET)
	public List<Object[]> stock() {
		List<Object[]> stockBooks = bookRepository.stockBooks();
		return stockBooks;
	}
	@RequestMapping(value = "/reportdatewise/datewisereport", method = RequestMethod.GET)
	public List<Object[]> DateWise(@RequestParam("fromDate") final String fromDate, @RequestParam("tillDate") final String tillDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date  date1  =  format.parse( fromDate );    
		Date  date2 =   format.parse( tillDate );  
		List<Object[]> dateWiseBooks = bookRepository.dateBasedStockList(date1,date2);
		return dateWiseBooks;
	}
	
	@RequestMapping(value = "/reportindividual/individualstock", method = RequestMethod.GET)
	public List<Object[]> individualStockList(@RequestParam("publisherId") final int publisherId,@RequestParam("languageId") final int languageId,@RequestParam("authorId") final int authorId,@RequestParam("redingLevelId") final int redingLevelId) {
		List<Object[]> booksInHand = bookRepository.individualStockBooks(publisherId, languageId, authorId, redingLevelId);
		return booksInHand;
	}

	@RequestMapping(value = "/booksinhandreport", method = RequestMethod.GET)
	public List<Object[]> booksInHand() {
		List<Object[]> booksInHand = bookRepository.bookInHand();
		return booksInHand;
	}
	@RequestMapping(value = "/booksoutreport", method = RequestMethod.GET)
	public List<Object[]> booksOutLibrary() {
		List<Object[]> booksOut = bookBorrowRepository.booksOut();
		return booksOut;
	}
	
	@RequestMapping(value = "/todaydelivery", method = RequestMethod.GET)
	public List<Object[]> TodayDelivery() {
		List<Object[]> todayDlivery = bookBorrowRepository.getDeliveryToday();
		return todayDlivery;
	}
	
	@RequestMapping(value = "/todaypickup", method = RequestMethod.GET)
	public List<Object[]> TodayPickup() {
		List<Object[]> todayPickup = bookBorrowRepository.getPickupToday();
		return todayPickup;
	}
	
	@RequestMapping(value = "/nextdaydelivery", method = RequestMethod.GET)
	public List<Object[]> nextDayDelivery() {
		List<Object[]> nextDayDelivery = bookBorrowRepository.getDeliveryNextDay();
		return nextDayDelivery;
	}
	
	@RequestMapping(value = "/nextdaypickup", method = RequestMethod.GET)
	public List<Object[]> nextDayPickup() {
		List<Object[]> nextDayPickup = bookBorrowRepository.getPickupNextDay();
		return nextDayPickup;
	}
	
	@RequestMapping(value = "/getundelivereditems", method = RequestMethod.GET)
	public List<Object[]> getundelivereditems() {
		List<Object[]> undeliveredItemsList = bookBorrowRepository.getundelivereditems();
		return undeliveredItemsList;
	}
	
	@RequestMapping(value = "/fullsubscribeuser", method = RequestMethod.GET)
	public List<Object[]> fullSubscribeUser() {
		List<Object[]> fullSubscribeUser = userSubscriptionRepository.getFullSubscripeUser();
		return fullSubscribeUser;
	}
	
	@RequestMapping(value = "/onlyregisteruser", method = RequestMethod.GET)
	public List<Object[]> onlyRegisterUser() {
		List<Object[]> onlyRegisterUser = userSubscriptionRepository.getOnlyRegisterUser();
		return onlyRegisterUser;
	}
	@RequestMapping(value = "/expireusers", method = RequestMethod.GET)
	public List<Object[]> ExpireUsers() {
		List<Object[]> onlyRegisterUser = userSubscriptionRepository.reportExpireUsers();
		return onlyRegisterUser;
	}
	
	@RequestMapping(value = "/reportplanbaseusers/planbaseuser", method = RequestMethod.GET)
	public List<Object[]> planWiseReport(@RequestParam("subscriptionId") final int subscriptionId) {
		List<Object[]> dateWiseBooks = userSubscriptionRepository.reportPlanbaseUser(subscriptionId);
		return dateWiseBooks;
	}
	@RequestMapping(value = "/reportuserarea/reportareausers", method = RequestMethod.GET)
	public List<Object[]> reportExpireUsers(@RequestParam("areaId")final int areaId) {
		List<Object[]> usersBasedPincode = userRepository.getUserBasedOnPincode(areaId);
		return usersBasedPincode;
	}
	@RequestMapping(value = "/reportstafftoday/staffdatereporttoday", method = RequestMethod.GET)
	public List<Object[]> StaffDateReport(@RequestParam("staffId") long staffId) {
	    List<Object[]> staffToadyReport = booksBorrowRepository.getStaffTodayReport(staffId);
		return staffToadyReport;
	}
	
	@RequestMapping(value = "/reportstafftoday/staffweekreport", method = RequestMethod.GET)
	public List<Object[]> staffReportWeek(@RequestParam("staffId") long staffId) {
	    List<Object[]> staffWeekReport = booksBorrowRepository.getStaffWeekReport(staffId);
		return staffWeekReport;
	}
	
	@RequestMapping(value = "/confirmpickpbook", method = RequestMethod.GET)
	public List<Object[]> confirmPickUpBook() {
		List<Object[]> confirmPickupBooks = booksBorrowRepository.confirmPickup();
		return confirmPickupBooks;
	}
	@RequestMapping(value = "/paymentconfirmation", method = RequestMethod.GET)
	public List<Object[]> paymentConfirmation() {
		List<Object[]> paymentconfirmation = userSubscriptionRepository.getPaymentpendingUsers();
		return paymentconfirmation;
	}
	@RequestMapping(value = "/payment/paymentstatus", method = RequestMethod.POST)
	public ResponseEntity<PaymentConfirm> PaymentConfirmation(@RequestParam("paymentstatusId")final int paymentstatusId) {
		UserSubscription usersubscription = userSubscriptionRepository.findById(paymentstatusId);
		PaymentConfirm confirmPayment = new PaymentConfirm();
		String status = "success";
		if(usersubscription != null) {	
			
			usersubscription.setPaymentStatus(status);
			userSubscriptionRepository.save(usersubscription);	
		}
		return new ResponseEntity(usersubscription,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/testimoialsreport", method = RequestMethod.GET)
	public List<Object[]> testimonialsReport() {
		List<Object[]> testimonial = testimonialRepository.getTestimonials();
		System.out.println("the tetimonials are"+testimonial.toString());
		return testimonial;
	}
	@RequestMapping(value = "/testimonials/confirmtestimonials", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> confirmTestimonials(@RequestParam("testimonialId") int testimonialId) {
		int value=1;
		Testimonial testimonial = testimonialRepository.findById(testimonialId);
		if(testimonial!=null) {
			testimonial.setIsApproved(value);
			testimonialRepository.save(testimonial);
		}
		return new ResponseEntity(testimonial, HttpStatus.OK);
	}
	
}
