package com.tansha.library.bookshelf.admin.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.tansha.library.bookshelf.admin.model.Area;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.repository.AuthorRepository;
import com.tansha.library.bookshelf.admin.repository.LanguageRepository;
import com.tansha.library.bookshelf.admin.repository.PublisherRepository;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.model.Book;
import com.tansha.library.bookshelf.admin.service.AuthorService;
import com.tansha.library.bookshelf.admin.service.BookCategoryService;
import com.tansha.library.bookshelf.admin.service.BookService;
import com.tansha.library.bookshelf.admin.service.LanguageService;
import com.tansha.library.bookshelf.admin.service.PublisherService;
import com.tansha.library.bookshelf.admin.service.ReadingLevelService;
import com.tansha.library.bookshelf.admin.validator.BookValidator;
import com.tansha.library.bookshelf.admin.validator.BookValidator;
import com.tansha.library.bookshelf.admin.repository.BookCategoryRepository;
import com.tansha.library.bookshelf.admin.repository.BookRepository;
import com.tansha.library.bookshelf.admin.repository.ReadingLevelRepository;

@Controller

public class BooksController {

	static Row.MissingCellPolicy CREATE_NULL_AS_BLANK;
	static Row.MissingCellPolicy RETURN_BLANK_AS_NULL;
	static Row.MissingCellPolicy RETURN_NULL_AND_BLANK;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private BookCategoryRepository bookCategoryRepository;

	@Autowired
	private ReadingLevelRepository readingLevelRepository;
	@Autowired
	private BookValidator bookValidator;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private BookCategoryService bookCategoryService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private ReadingLevelService readingLevelService;

	@RequestMapping(value = { "/managebook" }, method = RequestMethod.GET)
	public ModelAndView getAllBooks() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		for (GrantedAuthority a : auth.getAuthorities()) {
		}
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("managebook");

		return modelAndView;
	}

	@RequestMapping(value = { "/addbook" }, method = RequestMethod.GET)
	public ModelAndView addBook() {
		ModelAndView modelAndView = new ModelAndView();
		Book book = new Book();
		modelAndView.addObject("readingLevels", readingLevelRepository.findByIsActive(1));

		modelAndView.addObject("publishers", publisherRepository.findByIsActive(1));
		modelAndView.addObject("authors", authorRepository.findByIsActive(1));
		modelAndView.addObject("languages", languageRepository.findByIsActive(1));
		modelAndView.addObject("categories", bookCategoryRepository.findByIsActive(1));

		modelAndView.addObject("book", book);
		modelAndView.setViewName("addbook");
		return modelAndView;
	}

	@RequestMapping(value = { "/addbook" }, method = RequestMethod.POST)
	public ModelAndView addBook(@Valid Book book, BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		bookValidator.validate(book, bindingResult);

		if (bindingResult.hasErrors()) {
			modelAndView.addObject("readingLevels", readingLevelRepository.findByIsActive(1));

			modelAndView.addObject("publishers", publisherRepository.findByIsActive(1));
			modelAndView.addObject("authors", authorRepository.findByIsActive(1));
			modelAndView.addObject("languages", languageRepository.findByIsActive(1));
			modelAndView.addObject("categories", bookCategoryRepository.findByIsActive(1));

			modelAndView.setViewName("addbook");
		} else {
			bookService.addBook(book);
			modelAndView.setViewName("redirect:/managebook");
		}
		return modelAndView;
	}

	@RequestMapping(value = { "/updatebook/{bookId}" }, method = RequestMethod.GET)
	public ModelAndView updateBook(@PathVariable final int bookId) {
		ModelAndView modelAndView = new ModelAndView();
		Book book = bookRepository.findByBookId(bookId);

		modelAndView.addObject("readingLevels", readingLevelRepository.findByIsActive(1));

		modelAndView.addObject("publishers", publisherRepository.findByIsActive(1));
		modelAndView.addObject("authors", authorRepository.findByIsActive(1));
		modelAndView.addObject("languages", languageRepository.findByIsActive(1));
		modelAndView.addObject("categories", bookCategoryRepository.findByIsActive(1));
		modelAndView.addObject("book", book);
		modelAndView.setViewName("updatebook");
		return modelAndView;
	}

	@RequestMapping(value = { "/updatebook" }, method = RequestMethod.POST)
	public ModelAndView updateBook(@Valid Book book, BindingResult bindingResult, final HttpServletRequest request) {
		ModelAndView modelAndview = new ModelAndView();
		bookValidator.validate(book, bindingResult);
		modelAndview.addObject("readingLevels", readingLevelRepository.findByIsActive(1));

		modelAndview.addObject("publishers", publisherRepository.findByIsActive(1));
		modelAndview.addObject("authors", authorRepository.findByIsActive(1));
		modelAndview.addObject("languages", languageRepository.findByIsActive(1));
		modelAndview.addObject("categories", bookCategoryRepository.findByIsActive(1));
		System.out.println("eaturebook"+book.getIsFeaturedBook());
		System.out.println("eaturebook"+book.getAmount());
		System.out.println("eaturebook"+book.getIsActive());
		
		if (bindingResult.hasErrors()) {
			modelAndview.setViewName("updatebook");
		} else {
			bookService.updateBook(book);
			modelAndview.setViewName("redirect:/managebook");
		}

		return modelAndview;
	}

	@RequestMapping(value = "/deletebook/{bookId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final int bookId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Book book = bookRepository.findByBookId(bookId);
		if (book != null) {
			book.setIsActive(activeFlag);
			bookRepository.save(book);
			modelandview.setViewName("redirect:/managebook");
		} else {
			modelandview.setViewName("redirect:/managebook");
		}
		return modelandview;
	}

	@RequestMapping(value = "/exportbook", method = RequestMethod.GET)
	public ModelAndView exportbook() {

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("exportbook");
		return modelAndView;
	}

	@RequestMapping(value = "/clearbooks", method = RequestMethod.GET)
	public ModelAndView clearBooks() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		bookRepository.truncateBooks();
		modelAndView.addObject("clearMsg", "Successfully Cleared");
		
		modelAndView.setViewName("clearbooks");
	return modelAndView;	
	}
	
	
	@RequestMapping(value = "/exportbook", method = RequestMethod.POST)
	public ModelAndView mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile) throws IOException {
		ModelAndView modelAndView = new ModelAndView();

		XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
		Map<Integer, String> errors = new HashMap<Integer, String>();

		XSSFSheet worksheet = workbook.getSheetAt(0);
		int errorIndex = 0;
		int cellIndex = 0;
		for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			//System.out.println("row is "+i);
			XSSFRow row = worksheet.getRow(i);
			boolean isErrorExist = false;
			try {
				Book book = new Book();
				CellType cell = row.getCell(1).getCellTypeEnum();
				long intVal = 0;
				String stringVal = "";
				cellIndex = 1;
				switch (cell) {
				case NUMERIC:
					if (row.getCell(1) != null) {
						intVal = (long) row.getCell(1).getNumericCellValue();
					} else {
						errors.put(errorIndex, ".) ISBN Can't be empty in row # " + i + " cell index is "+ cellIndex);
						errorIndex += 1;
						isErrorExist = true;
					}
					break;
				case STRING:
					if (row.getCell(1) != null) {
						stringVal = row.getCell(1).getStringCellValue();
					} else {
						errors.put(errorIndex, ".) ISBN Can't be empty in row # " + i + " cell index is "+ cellIndex);
						errorIndex += 1;
						isErrorExist = true;
					}
					break;
				}
				if (intVal != 0) {
					stringVal = String.valueOf(intVal);
				}
				String isbnCode = stringVal;
				String bookTitle = "";
				String authorName = "";
				String publisherName = "";
				String bookCategory = "";
				String langugeName = "";
				String readingLevel = "";
				int price = 0;
				int noofPages = 0;
				cellIndex = 2;
				if (row.getCell(2) != null) {
					bookTitle = row.getCell(2).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) Book Title can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 4;
				if (row.getCell(4) != null) {
					authorName = row.getCell(4).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) Author Name can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 7;
				if (row.getCell(7) != null) {
					publisherName = row.getCell(7).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) Publisher Name can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 9;
				if (row.getCell(9) != null) {
					readingLevel = row.getCell(9).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) readingLevel can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 10;
				if (row.getCell(10) != null) {
					bookCategory = row.getCell(10).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) Book Category Can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 5;
				if (row.getCell(5) != null) {
					langugeName = row.getCell(5).getStringCellValue().trim();
				} else {
					errors.put(errorIndex, ".) Language Can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				cellIndex = 6;
				if (row.getCell(6) != null) {
					noofPages = (int) Math.round(row.getCell(6).getNumericCellValue());
				}
				cellIndex = 8;
				if (row.getCell(8) != null) {
					price = (int) Math.round(row.getCell(8).getNumericCellValue());
				} else {
					errors.put(errorIndex, ".) Price Can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				String bindingVal = "";
				cellIndex = 11;
				if (row.getCell(11) != null) {
					bindingVal = row.getCell(11).getStringCellValue();
				} else {
					bindingVal = "NA";
				}
				String longTitle = "";
				cellIndex = 13;
				if (row.getCell(3) != null) {
					longTitle = row.getCell(3).getStringCellValue();
				} else {

					longTitle = "NA";
				}

				String description = "";
				StringBuffer descriptionString = new StringBuffer("");
				cellIndex = 12;
				if (row.getCell(12) != null) {
					description = row.getCell(12).getStringCellValue();
					descriptionString.append(description);
				} else {

					description = "NA";
				}
				
				if (isbnCode == "" || isbnCode.equals("") || isbnCode.isEmpty()) {
					isErrorExist = true;
					errors.put(errorIndex, " ISBN Code Can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
				}
				if (bookTitle.isEmpty()) {
					errors.put(errorIndex, " bookTitle can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				if (authorName.isEmpty()) {
					errors.put(errorIndex, " authorName can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				if (publisherName.isEmpty()) {
					errors.put(errorIndex, " publisherName can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				if (readingLevel.isEmpty()) {
					errors.put(errorIndex, " readingLevel can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				
				if (bookCategory.isEmpty()) {
					errors.put(errorIndex, " bookCategory can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}
				if (langugeName.isEmpty()) {
					errors.put(errorIndex, " langugeName can't be empty in row # " + i + " cell index is "+ cellIndex);
					errorIndex += 1;
					isErrorExist = true;
				}

				book.setIsbncode(isbnCode);

				book.setBookTitle(bookTitle);
				Integer authorId = authorService.getAuthorByNameQuery(authorName);
				book.setAuthorID(authorId);

				Integer publsiherId = publisherService.getPublisherByNameQuery(publisherName);
				book.setPublisherID(publsiherId);

				Integer readingLevelId = readingLevelService.getReadingLevelQuery(readingLevel);
				book.setReadingLevelId(readingLevelId);
				
				Integer bookCategoryId = bookCategoryService.getBookCategoryByNameQuery(bookCategory);
				book.setCategoryID(bookCategoryId);

				Integer languageId = languageService.getLanguageByNameQuery(langugeName);
				book.setLanguageId(languageId);

				book.setNoofPages(noofPages);
				book.setAmount(price);
				if (bindingVal != "") {
					book.setBinding(bindingVal.trim());
				} else {
					book.setBinding("NA");
				}
				if (longTitle != "") {
					if (longTitle.length() > 500) {
						longTitle = longTitle.substring(0, 499);
					}

					book.setLongTitle(longTitle.trim());
				} else {
					book.setLongTitle("NA");
				}
				
				if (description != "") {
					if (description.length() > 5000) {
						description = description.substring(0, 4999);
					}

					book.setDescription(description);
				} else {
					book.setDescription("NA");
				}
				book.setIsActive(1);
				book.setIsBookBorrowed(0);
				book.setIsFeaturedBook(0);
				book.setIsLost(0);
				book.setPublishedYear(2015);
				//book.setReadingLevelId(1);
				if (!isErrorExist) {
					bookRepository.save(book);
				}

			} catch (Exception e) {
				
				// TODO: handle exception
				System.out.println("errors are " + e.getMessage());
				errors.put(errorIndex, ".)" + e.getMessage() + " Error " + i + " cell index is "+ cellIndex);
				errorIndex += 1;
			}

		}
		//System.out.println("Errors size is >>>>> " + errors.size());

		if (errors.isEmpty()) {
			//System.out.println("No Errors >>> ");
			modelAndView.addObject("successMessage", "Uploaded Successfully!!!");
		} else {
			//System.out.println("Errors");
			/*for (Map.Entry<Integer, String> entry : errors.entrySet()) {
		        System.out.println("\n\n" + entry.getKey() + ":" + entry.getValue() + "\n\n");
		    }*/
			
			modelAndView.addObject("errorMessage", "Errors are below");
			modelAndView.addObject("errors", errors);
		}
		modelAndView.setViewName("exportbook");
		return modelAndView;

	}

}
