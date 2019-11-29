package com.tansha.library.bookshelf.admin.validator;

import com.tansha.library.bookshelf.admin.model.User;
import com.tansha.library.bookshelf.admin.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
       
       
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if(!errors.hasFieldErrors("name")) {

        if (user.getName().length() < 6 || user.getName().length() > 30) {
            errors.rejectValue("name", "Size.userForm.firstName");
        }
        }        
      
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "locality", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "landmark", "NotEmpty");
       
        
       
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "NotEmpty");
       
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pincode", "NotEmpty");
        if(!errors.hasFieldErrors("pincode")) {

        if (String.valueOf(user.getPincode()).length() < 6) {
            errors.rejectValue("pincode", "Size.userForm.pincode");
        } 
        } 
        
       
        
        
       
        
        if (userService.findUserByEmail(user.getEmailId()) != null) {
            errors.rejectValue("emailId", "Duplicate.userForm.emailId");
        }
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "NotEmpty");
        if (user.getPhoneNumber() <= 0 ) {
            errors.rejectValue("phoneNumber", "Size.userForm.phoneNumber");
        }
        
        
        
        
    }
}
