package com.tansha.library.bookshelf.admin.utils;



import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

//import org.baeldung.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.tansha.library.bookshelf.admin.model.User;

@Component
public class EmailSender  {
   

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    // API

   

    public void confirmRegistration(final HttpServletRequest request,User user) {
        //final User user = event.getUser();
       
        
try {
        final SimpleMailMessage email = constructEmailMessage(request, user);
        
       
        mailSender.send(email);
}catch (Exception e) {
	// TODO: handle exception
	e.printStackTrace();
}
    }

    //

    public final SimpleMailMessage constructEmailMessage( final HttpServletRequest request, final User user) {
        final String recipientAddress = user.getEmailId();
        final String subject = "Registration Confirmation";
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        final String confirmationUrl = appUrl + "/emailtemplates/registrationConfirm.html?firstName=" + user.getName();
        //final String message = messages.getMessage("message.regSucc", null, request.getLocale());
        String message = "Successfully Registered !!!";
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setReplyTo("habeep2k1@gmail.com");
        Date date = new Date();
        email.setSentDate(date);
        email.setSubject(subject);
        email.setText(message );
        email.setFrom("habeep2k1@gmail.com");
        return email;
    }

}
