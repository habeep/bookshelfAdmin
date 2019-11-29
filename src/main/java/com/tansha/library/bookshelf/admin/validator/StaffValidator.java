package com.tansha.library.bookshelf.admin.validator;

import com.tansha.library.bookshelf.admin.model.Staff;
import com.tansha.library.bookshelf.admin.repository.StaffRepository;
import com.tansha.library.bookshelf.admin.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

import org.springframework.validation.Validator;

@Component
public class StaffValidator implements Validator {
	@Autowired
	private StaffService staffService;
	@Autowired
	private StaffRepository staffRepository;
	 private Pattern pattern;  
	 private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";
	String PINCODE_PATTERN = "[0-9]{6}";

	@Override
	public boolean supports(Class<?> aClass) {
		return Staff.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Staff staff = (Staff) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "staffName", "Size.userForm.emailId");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "NotEmpty");
		
		if (!(staff.getEmailId() != null && staff.getEmailId().isEmpty())) {  
			   pattern = Pattern.compile(EMAIL_PATTERN);  
			   matcher = pattern.matcher(staff.getEmailId());  
			   if (!matcher.matches()) {  
			    errors.rejectValue("emailId", "email.incorrect",  
			      "Enter a correct email");  
			   }  
			  }  
		
		if(staff.getRoleId() == -1 ) {
			errors.rejectValue( "roleId", "select.notEmpty");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "NotEmpty");
		if(staff.getMobileNumber() <= 0 ) {
			errors.rejectValue("mobileNumber", "NotEmpty","Mobile Number shouldn't be empty"); 
		} else  if (staff.getMobileNumber() != 0) {   // phone number validation  
		   pattern = Pattern.compile(MOBILE_PATTERN);  
		   String mobileNumbers = Long.toString(staff.getMobileNumber());
		   matcher = pattern.matcher(mobileNumbers);  
		   if (!matcher.matches()) {  
		    errors.rejectValue("mobileNumber", "mobileNumber.incorrect",  
		      "Enter a correct phone number");  
		   }  
		  }  
		  
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "town", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateofbirth", "NotEmpty");
		
		if(!staff.getDateofbirth().isEmpty() && staff.getDateofbirth() != null && !staff.getDateofbirth().equals("''") && staff.getDateofbirth() != " ") {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			//System.out.println("Date Value is here iF condition >>> "+staff.getDateofbirth());
			//System.out.println(formatter);
			LocalDate localDate = LocalDate.parse(staff.getDateofbirth(), formatter);
			//System.out.println(localDate);

			//System.out.println("YEAR is >>>> " + localDate.getYear());
			
			LocalDate currentDate = LocalDate.now();
			int localYear = localDate.getYear();
			int currentYear = currentDate.getYear();
			int age = currentYear - localYear;
			if(localDate.isAfter(currentDate)) {
				 errors.rejectValue("dateofbirth", "staff.dob.currentdate.error",  
					      "Date of birth should be less than current date and age should be greater than 24 years"); 
			} else if(age < 24 ) {
				 errors.rejectValue("dateofbirth", "staff.age.error",  
					      "Age Should be above 24 years..."); 
			}
			
		}
		
		
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode", "NotEmpty");
		
		// phone number validation  
		  if (!(staff.getPincode() != 0)) {  
		   pattern = Pattern.compile(PINCODE_PATTERN);  
		   String pincodes = Long.toString(staff.getPincode());
		   matcher = pattern.matcher(pincodes);  
		   if (!matcher.matches()) {  
		    errors.rejectValue("pincode", "pincode.incorrect",  
		      "Enter a correct pincode");  
		   }  
		  }
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idCardNumber", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idCard", "NotEmpty");
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

	}
}
