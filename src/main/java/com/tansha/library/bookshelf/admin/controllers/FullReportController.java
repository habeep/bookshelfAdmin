package com.tansha.library.bookshelf.admin.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.sl.usermodel.ObjectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.model.BookBorrow;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.Testimonial;
import com.tansha.library.bookshelf.admin.repository.AreaRepository;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.BooksBorrowRepository;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRepository;
import com.tansha.library.bookshelf.admin.repository.TestimonialRepository;
import com.tansha.library.bookshelf.admin.repository.UserSubscriptionRepository;
@Controller
public class FullReportController {
	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TestimonialRepository testimonialRepository;

	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private LanguageRepository languageRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private StaffRepository StaffRepository;
	
	@Autowired
	private BooksBorrowRepository booksBorrowRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	@Autowired
	private ReadingLevelRepository readingLevelRepository;
	
	@RequestMapping(value = { "/reporttotalstock" }, method = RequestMethod.GET)
	public ModelAndView getAllBooks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("stockBooks", bookRepository.stockBooks());
		modelAndView.setViewName("reporttotalstock");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportdatewise" }, method = RequestMethod.GET)
	public ModelAndView manageDateWise() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportdatewise");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportindividual" }, method = RequestMethod.GET)
	public ModelAndView individualStock() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("publishers", publisherRepository.findAll());
		modelAndView.addObject("languages", languageRepository.findAll());
		modelAndView.addObject("authors", authorRepository.findAll());
		modelAndView.addObject("readingLevels", readingLevelRepository.findAll());
		modelAndView.setViewName("reportindividual");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportbooksinhand" }, method = RequestMethod.GET)
	public ModelAndView manageHandBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportbooksinhand");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportbooksout" }, method = RequestMethod.GET)
	public ModelAndView manageOutBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportbooksout");
		return modelAndView;
	}
	@RequestMapping(value = { "/manageoverdue" }, method = RequestMethod.GET)
	public ModelAndView manageOverDue() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("manageoverdue");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportstafftoday" }, method = RequestMethod.GET)
	public ModelAndView manageEmployee() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("staffs", StaffRepository.findByIsActive(1));
		modelAndView.setViewName("reportstafftoday");
		return modelAndView;
	}
	@RequestMapping(value = { "/reporttodaydelivery" }, method = RequestMethod.GET)
	public ModelAndView manageTodayDelivery() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reporttodaydelivery");
		return modelAndView;
	}
	@RequestMapping(value = { "/reporttodaypickup" }, method = RequestMethod.GET)
	public ModelAndView manageTodayPickup() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reporttodaypickup");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportnextdaydelivery" }, method = RequestMethod.GET)
	public ModelAndView manageNextDayDelivery() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportnextdaydelivery");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportnextdaypickup" }, method = RequestMethod.GET)
	public ModelAndView manageNextPickup() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportnextdaypickup");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportundelivered" }, method = RequestMethod.GET)
	public ModelAndView getUndeliveredList() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportundelivered");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/reportfullsubscribeuser" }, method = RequestMethod.GET)
	public ModelAndView manageNotSubscribeMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportfullsubscribeuser");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportonlyregisteruser" }, method = RequestMethod.GET)
	public ModelAndView manageAreaWiseMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportonlyregisteruser");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportplanbaseusers" }, method = RequestMethod.GET)
	public ModelAndView managePlanWiseMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("subscriptions", subscriptionRepository.findAll());
		modelAndView.setViewName("reportplanbaseusers");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportexpireusers" }, method = RequestMethod.GET)
	public ModelAndView reportExpireUsers() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("reportexpireusers");
		return modelAndView;
	}
	@RequestMapping(value = { "/reportuserarea" }, method = RequestMethod.GET)
	public ModelAndView manageChargeMember() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pincodes",areaRepository.findAll());
		modelAndView.setViewName("reportuserarea");
		return modelAndView;
	}
	@RequestMapping(value = { "/managecash" }, method = RequestMethod.GET)
	public ModelAndView manageCashAndCard() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("managecash");
		return modelAndView;
	}
	@RequestMapping(value = { "/confirmpickup" }, method = RequestMethod.GET)
	public ModelAndView confirmPickup() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("confirmpickup");
		return modelAndView;
	}
	@RequestMapping(value = { "/payment" }, method = RequestMethod.GET)
	public ModelAndView Payment() {
		ModelAndView modelAndView = new ModelAndView();
		List<Object[]> paymentconfirmation = userSubscriptionRepository.getPaymentpendingUsers();
		modelAndView.addObject("paymentconfirmation",paymentconfirmation);
		modelAndView.setViewName("payment");
		return modelAndView;
	}
	
	@RequestMapping(value = "/print", method = RequestMethod.GET)
	public ModelAndView printId() {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("enter the print controller");
		String index = "";
		List<Integer> printBookId = bookRepository.getBookPrintId();
		List<String> formattedBookIds = new ArrayList<String>();
		String formatId = "";
		for(Integer bookId:printBookId) {
			formatId= bookId.toString(); 
			formatId = ("00000"+formatId).substring(formatId.length());
			formatId = "BS"+formatId;
			formattedBookIds.add(formatId);
		}
			/*for(int i=1;i<=printBookId.size();i++) {
				//index = "BS100"+i;
				index = ("0000"+ i ).substring(i.le, endIndex)
				formattedBookIds.add(index);
			
			}*/
			System.out.println("the book id index are"+formattedBookIds.size());
			modelAndView.addObject("formattedBookIds",formattedBookIds);
			modelAndView.setViewName("print");
			return modelAndView;
	}
	@RequestMapping(value = { "/mydelivery" }, method = RequestMethod.GET)
	public ModelAndView mydelivery() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = StaffRepository.findByEmailId(auth.getName());
	    long staffId = staff.getId();
	    List<Object[]> deliverLists = booksBorrowRepository.getMyAllocatedDeliveriesList(staffId);
	    modelAndView.addObject("deliverLists",deliverLists);
		modelAndView.setViewName("mydelivery");
		return modelAndView;
	}
	@RequestMapping(value = { "/testimonials" }, method = RequestMethod.GET)
	public ModelAndView testimonialsReport() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("testimonials");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/deletetestimonial/{testiId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int testiId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Testimonial testimonial = testimonialRepository.findById(testiId);
		if (testimonial != null) {
			testimonial.setIsActive(activeFlag);
			testimonialRepository.save(testimonial);
			modelandview.setViewName("redirect:/testimonials");
		} else {
			modelandview.setViewName("redirect:/testimonials");
		}
		return modelandview;
	}

	
}
