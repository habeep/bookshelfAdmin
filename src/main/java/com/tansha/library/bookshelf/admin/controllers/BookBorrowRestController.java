package com.tansha.library.bookshelf.admin.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.mail.MessagingException;

import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tansha.library.bookshelf.admin.exceptions.Err;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.Mail;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.User;
import com.tansha.library.bookshelf.admin.model.UserBookCart;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.repository.UserRepository;
import com.tansha.library.bookshelf.admin.service.EmailService;
import com.tansha.library.bookshelf.admin.service.UserBookCartService;






@RestController
public class BookBorrowRestController {
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private UserBookCartService ubcService;
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/confirmdelivery", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> Mydelivery(@RequestParam("borrowId") int borrowId) {
		Date date = new Date();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowId);
		if(bookBorrow!=null) {
			bookBorrow.setDeliveredOn(date);
			booksBorrowRepository.save(bookBorrow);
		}
		System.out.println("\n\n saved!!!!!!1 \n\n");
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkemail", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> checkEmail(@RequestParam("borrowId") int borrowId,@RequestParam("unDeliveryReason") String unDeliveryReason) throws Exception {
		Date date = new Date();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowId);
		System.out.println("BookBorrow Object Value >>> "+bookBorrow.toString()+"\n\n");
		Book book = bookRepository.findByBookId((bookBorrow.getBookID()));
		System.out.println("Book Model >>> "+book.toString()+"\n\n");
		User user = userRepository.findById( bookBorrow.getUserID());
		StringBuilder content = new StringBuilder();
		content.append("Book checkout Summary!" + "<br/>");
		sendCheckoutEmail("habeep2k1@gmail.com", "Bookshelf-library.com : UnDelivered Notification !!!",
				user.getName(), content.toString(), "/emailTemplatess/email", book,  bookBorrow.getOrderId(), user,bookBorrow.getUnDeliveryReason());
		
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}
	@RequestMapping(value = "/undelivery", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> UndeliveryItems(@RequestParam("borrowId") int borrowId,@RequestParam("unDeliveryReason") String unDeliveryReason) throws Exception {
		Date date = new Date();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowId);
		System.out.println("BookBorrow Object Value >>> "+bookBorrow.toString()+"\n\n");
		Book book = bookRepository.findByBookId((bookBorrow.getBookID()));
		System.out.println("Book Model >>> "+book.toString()+"\n\n");
		User user = userRepository.findById( bookBorrow.getUserID());
		StringBuilder content = new StringBuilder();
		content.append("Book checkout Summary!" + "<br/>");
		System.out.println("Return type >>> "+bookBorrow.getType_return()+"\n\n");
		if(bookBorrow!=null) {
			if(bookBorrow.getType_return() == 0 ) {
			bookBorrow.setBookUnDeliveryStatus(1);
			bookBorrow.setUnDeliveryReason(unDeliveryReason);
			bookBorrow.setActualDateofReturned(date);
			bookBorrow.setBookReturnStatus(1);
			bookBorrow.setDeliveredOn(date);
			
			bookBorrow.setConfirmBookReturnStatus(1);
			booksBorrowRepository.save(bookBorrow);
			if(book !=null) {
				book.setIsBookBorrowed(0);
				bookRepository.save(book);
			}
			sendCheckoutEmail(user.getEmailId(), "Bookshelf-library.com : UnDelivered Notification !!!",
				user.getName(), content.toString(), "/emailTemplatess/email", book,  bookBorrow.getOrderId(), user,bookBorrow.getUnDeliveryReason());
			
			/*sendEmail(user.getEmailId(), "BookShelf-library.com: UnDelivered Notification Email !!!", user.getEmailId(),
					content, "/emailTemplatess/email-welcome",bookBorrow.getUnDeliveryReason());*/
			} else {
				/*java.util.Date date2 = null;*/
				//UPDATE `library-mgmt`.`tbl_borrow_master` SET `actualDateofReturned` = NULL WHERE (`borrowID` = '70'); 
				/*bookBorrow.setReturnRequestedON(date2);
				bookBorrow.setBookReturnStatus(0);
				bookBorrow.setType_return(0);*/
				System.out.println("inside pick up undelivered status");
				//booksBorrowRepository.updatePickupUnDeliveredStatus(bookBorrow.getBorrowID());
				System.out.println("inside pick up undelivered status 1");
				//Err err = ubcService.addUserBookToCart(new UserBookCart(user.getId(), bookBorrow.getBookID(), 1));
				System.out.println("inside pick up undelivered status 2");
				sendCheckoutEmail(user.getEmailId(), "Bookshelf-library.com : UnDelivered Notification !!!",
						user.getName(), content.toString(), "/emailTemplatess/email", book,  bookBorrow.getOrderId(), user,bookBorrow.getUnDeliveryReason());

			}
			/*Mail mail = new Mail();
			mail.setFrom(env.getProperty("support.email"));
			mail.setTo(user.getEmailId());
			mail.setSubject("BookShelf Registered!!!");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", user.getName());
			model.put("signature", "http://bookshelf-library.com/");
			model.put("content", "You have successfully registered.");
			mail.setModel(model);
			emailService.sendSimpleMessage(mail, "email-undelivered");*/
			

			
		}
		
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/unpickup", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> UnpickupItems(@RequestParam("borrowId") int borrowId,@RequestParam("unDeliveryReason") String unDeliveryReason)  {
		System.out.println("borrowId:="+borrowId+ "---- unDeliveryReason:="+unDeliveryReason);
		Date date = new Date();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowId);
		System.out.println("BookBorrow Object Value >>> "+bookBorrow.toString()+"\n\n");
		Book book = bookRepository.findByBookId((bookBorrow.getBookID()));
		System.out.println("Book Model >>> "+book.toString()+"\n\n");
		User user = userRepository.findById( bookBorrow.getUserID());
		StringBuilder content = new StringBuilder();
		content.append("Book checkout Summary!" + "<br/>");
		System.out.println("Return type >>> "+bookBorrow.getType_return()+"\n\n");
		if(bookBorrow!=null) {
			
				/*java.util.Date date2 = null;*/
				//UPDATE `library-mgmt`.`tbl_borrow_master` SET `actualDateofReturned` = NULL WHERE (`borrowID` = '70'); 
				/*bookBorrow.setReturnRequestedON(date2);
				bookBorrow.setBookReturnStatus(0);
				bookBorrow.setType_return(0);*/
				System.out.println("inside pick up undelivered status"+"\n\n");
				try {
					System.out.println("inside pick up undelivered status 1"+"\n\n");
					Err err = ubcService.addUserBookToCart(new UserBookCart(user.getId(), bookBorrow.getBookID(), 1));
					System.out.println("inside pick up undelivered status 2"+"\n\n");
				  booksBorrowRepository.updatePickupUnDeliveredStatus(unDeliveryReason,bookBorrow.getBorrowID());
				//System.out.println("flagUpdateStatus="+flagUpdateStatus+"\n\n");
				System.out.println("inside pick up undelivered status 3 >> "+ "\n\n");
				
				}catch (Exception e) {
					// TODO: handle exception
					System.out.println("exception >>> "+e.getMessage()+"\n\n");
					System.out.println(e+"\n\n");
				}
				System.out.println("inside pick up undelivered status 4"+"\n\n");
				/*sendCheckoutEmail(user.getEmailId(), "Bookshelf-library.com : UnDelivered Notification !!!",
						user.getName(), content.toString(), "/emailTemplatess/email", book,  bookBorrow.getOrderId(), user,bookBorrow.getUnDeliveryReason());
*/
			
			/*Mail mail = new Mail();
			mail.setFrom(env.getProperty("support.email"));
			mail.setTo(user.getEmailId());
			mail.setSubject("BookShelf Registered!!!");
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", user.getName());
			model.put("signature", "http://bookshelf-library.com/");
			model.put("content", "You have successfully registered.");
			mail.setModel(model);
			emailService.sendSimpleMessage(mail, "email-undelivered");*/
			

			
		}
		
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}
	
	public void sendCheckoutEmail(String userEmailId, String subject, String userName, String content,
			String templatePath, Book books, long orderId, User user,String undeliveredReason) throws MessagingException, Exception {

		Mail mail = new Mail();
		List<Book> bookObj = new ArrayList<Book>();
		bookObj.add(books);
		mail.setFrom(env.getProperty("support.email"));
		mail.setTo(userEmailId);
		mail.setSubject(subject);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", userName + " , ");
		model.put("books", bookObj);
		

		model.put("orderNumber", orderId);
		model.put("undeliveredReason", undeliveredReason);

		model.put("houseNumber", user.getHouseNumber());
		model.put("street", user.getStreet());
		model.put("locality", user.getLocality());
		model.put("pincode", user.getPincode());

		model.put("signature", "http://bookshelf-library.com/");
		model.put("content", content);
		mail.setModel(model);

		emailService.sendSimpleMessage(mail, templatePath);
	}

	
	public void sendEmail(String userEmailId, String subject, String userName, String content, String templatePath,String undeliveryReason)
			throws MessagingException, Exception {

		Mail mail = new Mail();
		mail.setFrom(env.getProperty("support.email"));
		mail.setTo(userEmailId);
		mail.setSubject(subject);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", userName + " , ");
		model.put("undeliveryReason", undeliveryReason);
		model.put("signature", "http://bookshelf-library.com/");
		model.put("content", content);
		mail.setModel(model);

		emailService.sendSimpleMessage(mail, templatePath);
	}

	
	@RequestMapping(value = "/confirmpickup", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> ConfirmPickup(@RequestParam("borrowId") int borrowId) {
		Date date = new Date();
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowId);
		//Book book = bookRepository.findByBookId((bookBorrow.getBookID()));
		if(bookBorrow!=null) {
			bookBorrow.setActualDateofReturned(date);
			bookBorrow.setBookReturnStatus(1);
			booksBorrowRepository.save(bookBorrow);
			/*book.setIsBookBorrowed(0);
			bookRepository.save(book);*/
		}
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/myupcomingdeliverieslist", method = RequestMethod.GET)
	public List<Object[]> MyUpcomingDelivery() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyAllocatedDeliveriesList(staffId);
	    return deliverList;
}
	
	@RequestMapping(value = "/adminupcomingdeliverieslist", method = RequestMethod.GET)
	public List<Object[]> AdminUpcomingDelivery() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  
	    
	    List<Object[]> deliverList = booksBorrowRepository.getAdminUpcomingDeliveriesList();
	    return deliverList;
}
	
	@RequestMapping(value = "/mycompletedeliverieslist", method = RequestMethod.GET)
	public List<Object[]> MyCompleteDlivery() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyCompletedDeliveriesList(staffId);
	    return deliverList;
}
	@RequestMapping(value = "/myupcomingpickupslist", method = RequestMethod.GET)
	public List<Object[]> MyUpcomingPickup() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyAllocatedPickupsList(staffId);
	    return deliverList;
}
	
	@RequestMapping(value = "/mycompletepickupslist", method = RequestMethod.GET)
	public List<Object[]> MyCompletePickup() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyCompletedPickupsList(staffId);	    
	    return deliverList;
}
	
	@RequestMapping(value = "/deliveryorderdetailsLists/{orderId}", method = RequestMethod.GET)
	public List<Object[]> orderDetails(@PathVariable final String orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long orderIdLong = Long.parseLong(orderId);
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyAllocatedDeliveryDetailsList(staffId,orderIdLong);
	    return deliverList;
}
	
	@RequestMapping(value = "/admindeliveryorderdetailsLists/{orderId}", method = RequestMethod.GET)
	public List<Object[]> adminorderDetails(@PathVariable final String orderId) {
		
	    long orderIdLong = Long.parseLong(orderId);
	   
	    List<Object[]> deliverList = booksBorrowRepository.getOrderDeliveryDetailsListForAdmin(orderIdLong);
	    return deliverList;
}
	
	
	@RequestMapping(value = "/pickuporderdetailsLists/{orderId}", method = RequestMethod.GET)
	public List<Object[]> pickupDetails(@PathVariable final String orderId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    long orderIdLong = Long.parseLong(orderId);
	    long staffId = staff.getId();
	    List<Object[]> deliverList = booksBorrowRepository.getMyAllocatedPickupDetailsList(staffId, orderIdLong);
	    return deliverList;
}
	

	@RequestMapping(value = "/confirmpickup/confirmedbook", method = RequestMethod.POST)
	public ResponseEntity<BookBorrow> confirmPickup(@RequestParam("borrowID") int borrowID) {
		int value=1;
		BookBorrow bookBorrow = booksBorrowRepository.findByBorrowID(borrowID);
		Book book = bookRepository.findByBookId((bookBorrow.getBookID()));
		if(bookBorrow!=null) {
			bookBorrow.setConfirmBookReturnStatus(value);
			booksBorrowRepository.save(bookBorrow);
			
			book.setIsBookBorrowed(0);
			bookRepository.save(book);
			
		}
		return new ResponseEntity(bookBorrow, HttpStatus.OK);
	}
}
