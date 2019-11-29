package com.tansha.library.bookshelf.admin.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import com.instamojo.wrapper.exception.HTTPException;
import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.exception.ConnectionException;
import com.tansha.library.bookshelf.admin.exceptions.Err;
import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.model.Author;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.Children;
import com.tansha.library.bookshelf.admin.model.Mail;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.User;
import com.tansha.library.bookshelf.admin.model.UserBookCart;
import com.tansha.library.bookshelf.admin.model.UserSubscription;
import com.tansha.library.bookshelf.admin.repository.AreaRepository;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.DeliverySlotRepository;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRepository;
import com.tansha.library.bookshelf.admin.repository.UserSubscriptionRepository;
import com.tansha.library.bookshelf.admin.service.BookCategoryService;
import com.tansha.library.bookshelf.admin.service.BookService;
import com.tansha.library.bookshelf.admin.service.EmailService;
import com.tansha.library.bookshelf.admin.service.UserBookCartService;
import com.tansha.library.bookshelf.admin.service.UserService;
import com.tansha.library.bookshelf.admin.utils.EmailSender;
import com.tansha.library.bookshelf.admin.validator.UserRegistrationValidator;
import com.tansha.library.bookshelf.admin.validator.UserValidator;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	@Autowired
	private BookService bookService;
	@Autowired
	private BookCategoryService bookCategoryService;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Autowired
	private ReadingLevelRepository readingLevelRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	@Autowired
	private UserSubscriptionRepository userSubscriptionRepository;

	@Autowired
	DeliverySlotRepository deliverySlotRepository;
	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserRegistrationValidator userRegistrationValidator;

	@Autowired
	private Environment env;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private EmailService emailService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	UserBookCartService ubcService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private StaffRepository staffRepository;

	@RequestMapping(value = { "/login", "/" }, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();

		Staff staff = new Staff();
		modelAndView.addObject("user", staff);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Staff staffExist = staffRepository.findByEmailId(auth.getName());
		if (staffExist != null) {
			modelAndView.setViewName("redirect:/managebook");
		} else {
			modelAndView.setViewName("login");
		}
		return modelAndView;
	}

	

	// Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:/login");
	}

}
