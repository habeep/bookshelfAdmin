package com.tansha.library.bookshelf.admin.validator;

import com.tansha.library.bookshelf.admin.model.SubscriptionRule;
import com.tansha.library.bookshelf.admin.repository.SubscriptionRuleRepository;
import com.tansha.library.bookshelf.admin.service.SubscriptionRuleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;

@Component
public class SubscriptionRuleValidator implements Validator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";

	@Override
	public boolean supports(Class<?> aClass) {
		return SubscriptionRule.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		SubscriptionRule subscriptionrule = (SubscriptionRule) o;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ruleName", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "noofMonths", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxNumberofBooks", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "maxNumberofDelivery", "NotEmpty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rule", "NotEmpty");
	}
}
