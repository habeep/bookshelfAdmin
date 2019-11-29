package com.tansha.library.bookshelf.admin.validator;

import com.tansha.library.bookshelf.admin.model.DeliverySlot;
import com.tansha.library.bookshelf.admin.repository.DeliverySlotRepository;
import com.tansha.library.bookshelf.admin.service.DeliverySlotService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import org.springframework.validation.Validator;

@Component
public class DeliverySlotValidator implements Validator {
	@Autowired
	private DeliverySlotService deliveryslotService;
	@Autowired
	private DeliverySlotRepository deliveryslotRepository;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	String ID_PATTERN = "[0-9]+";
	String STRING_PATTERN = "[a-zA-Z]+";
	String MOBILE_PATTERN = "[0-9]{10}";

	@Override
	public boolean supports(Class<?> aClass) {
		return DeliverySlot.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		DeliverySlot deliveryslot = (DeliverySlot) o;
		Calendar now = Calendar.getInstance();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateOfdelivery", "NotEmpty");
		// System.out.println("deliveryslot >>> "+deliveryslot.getDateOfdelivery());

		// if(deliveryslot.getDateOfdelivery())
		if (!deliveryslot.getDateOfdelivery().isEmpty() && deliveryslot.getDateOfdelivery() != null
				&& !deliveryslot.getDateOfdelivery().equals("''") && deliveryslot.getDateOfdelivery() != " ") {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			LocalDate localDate = LocalDate.parse(deliveryslot.getDateOfdelivery(), formatter);
			LocalDate currentDate = LocalDate.now();
			if (!localDate.isAfter(currentDate) && !localDate.isEqual(currentDate)) {
				errors.rejectValue("dateOfdelivery", "deliveryslot.dateofdelivery.currentdate.error",
						"Delivery date should be greater than current date or todays date.");
			}

		}

		if (deliveryslot.getAreaID() == -1) {
			errors.rejectValue("areaID", "size.deliveryslotform.areaid.empty");
		}

		if (deliveryslot.getStaffID() == -1) {
			errors.rejectValue("staffID", "size.deliveryslotform.staffid.empty");
		}

		if (deliveryslot.getDeliveryTimeFrom() == 0) {
			errors.rejectValue("deliveryTimeFrom", "size.deliveryslotform.timefrom.empty");
		}
		if (deliveryslot.getDeliveryTimeTill() == 0) {
			errors.rejectValue("deliveryTimeTill", "size.deliveryslotform.timeto.empty");
		}
		int time1=deliveryslot.getDeliveryTimeFrom();
		int time2=deliveryslot.getDeliveryTimeTill();
		if(time1==time2) {
			errors.rejectValue("deliveryTimeTill", "select.sameTime");
			
		}else if(time1>=time2) {
			errors.rejectValue("deliveryTimeTill", "select.sameTime.long");
		}

	}
}
