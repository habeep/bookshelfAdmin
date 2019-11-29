package com.tansha.library.bookshelf.admin.controllers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.model.Role;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.RoleRepository;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.service.StaffService;
import com.tansha.library.bookshelf.admin.validator.StaffValidator;


@Controller
public class StaffController {
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StaffRepository staffRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
    private StaffValidator staffValidator;
	
	@RequestMapping(value = { "/managestaff" }, method = RequestMethod.GET)
	public ModelAndView getAllStaffs() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("managestaff");
		return modelAndView;
	}

	@RequestMapping(value = { "/addstaff" }, method = RequestMethod.GET)
	public ModelAndView addStaff() {
		ModelAndView modelAndView = new ModelAndView();
		Staff staff = new Staff();
		List<Role> roles =  roleRepository.findByIsActive(1);
		modelAndView.addObject("roles", roles);
		modelAndView.addObject("staff", staff);
		modelAndView.setViewName("addstaff");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/addstaff" }, method = RequestMethod.POST)
	public ModelAndView addStaff(@Valid Staff staff,BindingResult bindingResult, final HttpServletRequest request) throws ParseException {
		ModelAndView modelAndView = new ModelAndView();

		staffValidator.validate(staff, bindingResult);
		
		List<Role> roles =  roleRepository.findByIsActive(1);
		modelAndView.addObject("roles", roles);
	
		 if(staffRepository.findByEmailId(staff.getEmailId()) != null) {
            bindingResult
                    .rejectValue("emailId", "error.user",
                            "There is already a user registered with the email provided");
        }


		if(bindingResult.hasErrors()) {
			modelAndView.setViewName("addstaff");
		} else {
			staffService.addStaff(staff);
			modelAndView.setViewName("redirect:/managestaff");
		}
		return modelAndView;
	}
	  
	
	@RequestMapping(value = { "/updatestaff/{staffId}" }, method = RequestMethod.GET)
	public ModelAndView updateStaff(@PathVariable final int staffId) {
		ModelAndView modelAndView = new ModelAndView();
		Staff staff = staffRepository.findById(staffId).get();
		List<Role> roles =  roleRepository.findByIsActive(1);
		modelAndView.addObject("roles", roles);
		
		modelAndView.addObject("staff", staff );
		modelAndView.setViewName("updatestaff");
		return modelAndView;
	}
	
	@RequestMapping(value = { "/updatestaff" }, method = RequestMethod.POST)
	public ModelAndView updateStaff(@Valid Staff staff,BindingResult bindingResult, final HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView();
//		System.out.println("enter the staff id is"+staff.getId());
//		System.out.println("the staff email id is"+staff.getEmailId());
		System.out.println("the staff email id is"+staff.getDateofbirth());
		staffValidator.validate(staff, bindingResult);
		List<Role> roles =  roleRepository.findByIsActive(1);
		modelAndView.addObject("roles", roles);
        
		if(bindingResult.hasErrors()) {
			
			modelAndView.setViewName("updatestaff");
		} else {
			staffService.updateStaff(staff);
			modelAndView.setViewName("redirect:/managestaff");
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/deletestaff/{staffId}/{activeFlag}", method = RequestMethod.GET)
	public ModelAndView deleteReligion(@PathVariable final long staffId, @PathVariable final int activeFlag) {
		ModelAndView modelandview = new ModelAndView();
		Staff staff = staffRepository.findById(staffId).get();
		
		if (staff != null) {
			staff.setIsActive(activeFlag);
			staffRepository.save(staff);
			modelandview.setViewName("redirect:/managestaff");
		} else {
			modelandview.setViewName("redirect:/managestaff");
		}
		return modelandview;
	}
	
	@RequestMapping(value = { "/changepassword" }, method = RequestMethod.GET)
	public ModelAndView changepassword() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    List<Staff> staffs = staffRepository.findByIsActive(1);
	    modelAndView.addObject("staffs", staffs);
	    modelAndView.addObject("staffId", staff.getId());
	  
		modelAndView.setViewName("changepassword");
		return modelAndView;
	}
	
	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
 	public ModelAndView changepassword(ModelAndView modelAndView, @RequestParam("staffId") final int staffId, @RequestParam("newpassword") final String newpassword,@RequestParam("confirmnewpassword") final String confirmnewpassword) {
 		// Find the user associated with the reset token
    	//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    List<Staff> staffs = staffRepository.findByIsActive(1);
	    modelAndView.addObject("staffs", staffs);
    	String errorMsg= "";
    	if (newpassword == null || newpassword.equals("") || newpassword == "") {
    		errorMsg += "Password shouldn't be empty <br/>";
    	} else if(!newpassword.equals(confirmnewpassword)) {
    		errorMsg += "Oops!  Confirm password must match with new password.<br/>";
    	} 
    	if(staffId == -1 ) {
    			errorMsg += "Staff shouldn't be empty";
    	}
    	if(errorMsg != "") {
    		modelAndView.addObject("errorMessage", errorMsg);
    		modelAndView.setViewName("changepassword");
    		return modelAndView;
    		
    	}
    	    	Staff staff = staffRepository.findById(staffId).get();
    	    	modelAndView.addObject("user", staff);
    	
    	if (staff != null) {
 			
    			
 			// Set new password    
             staff.setPassword((newpassword));
             
 			// Set the reset token to null so it cannot be used again
 			

 			// Save user
 			staffService.updateStaffPassword(staff);

 			// In order to set a model attribute on a redirect, we must use
 			// RedirectAttributes
 			//redir.addFlashAttribute("successMessage", "You have successfully changed your password.");
 			modelAndView.addObject("successMessage", "You have successfully changed your password.");
 			modelAndView.setViewName("changepassword");
 			return modelAndView;
 			
 		} else  {
 			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
 			modelAndView.setViewName("changepassword");	
 		}
 		
 		return modelAndView;
    }
	
	@RequestMapping(value = { "/changepasswordforuser" }, method = RequestMethod.GET)
	public ModelAndView changepasswordforuser() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    Staff staff = staffRepository.findByEmailId(auth.getName());
	    List<Staff> staffs = staffRepository.findByIsActive(1);
	    modelAndView.addObject("staffs", staffs);
	    modelAndView.addObject("staffId", staff.getId());
	  
		modelAndView.setViewName("changepasswordforuser");
		return modelAndView;
	}
	
	@RequestMapping(value = "/changepasswordforuser", method = RequestMethod.POST)
 	public ModelAndView changepasswordforuser(ModelAndView modelAndView,  @RequestParam("newpassword") final String newpassword,@RequestParam("confirmnewpassword") final String confirmnewpassword, RedirectAttributes redir) {

 		// Find the user associated with the reset token
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	Staff staff = staffRepository.findByEmailId(auth.getName());
	    List<Staff> staffs = staffRepository.findByIsActive(1);
	    modelAndView.addObject("staffs", staffs);
    	modelAndView.addObject("user", staff);
    	
    	
 		// This should always be non-null but we check just in case
    	if (newpassword == null || newpassword.equals("") || newpassword == "") {
    		modelAndView.addObject("errorMessage", "Password shouldn't be empty.");
    		modelAndView.setViewName("changepasswordforuser");
 			return modelAndView;
    	} else if(!newpassword.equals(confirmnewpassword)) {
    		modelAndView.addObject("errorMessage", "Oops!  Confirm password must match with new password.");
    		modelAndView.setViewName("changepasswordforuser");
 			return modelAndView;
    	} else if (staff != null) {
 			
    			
 			// Set new password    
             staff.setPassword((newpassword));
             
 			// Set the reset token to null so it cannot be used again
 			

 			// Save user
 			staffService.updateStaffPassword(staff);

 			// In order to set a model attribute on a redirect, we must use
 			// RedirectAttributes
 			redir.addFlashAttribute("successMessage", "You have successfully changed your password.");
 			modelAndView.addObject("successMessage", "You have successfully changed your password.");
 			modelAndView.setViewName("changepasswordforuser");
 			return modelAndView;
 			
 		} else  {
 			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
 			modelAndView.setViewName("changepassword");	
 		}
 		
 		return modelAndView;
    }
	
}
