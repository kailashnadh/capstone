package com.restaurant.restaurant_management.mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class SMTPMail {
	@Autowired
    private JavaMailSender javaMailSender;
	public void  sendEmail(String mail, String firstName,String lastName) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);
        
        msg.setSubject("Welcome to restaurant management app");
        msg.setText("Hello "+firstName + " "+ lastName+","
        		+ "Your Account has been sucessfully Created."
        		+ "Your Default Credentials are "
        		+ "Username- "+mail+""
        		+ "password-"+mail);

        javaMailSender.send(msg);

    }
	
}