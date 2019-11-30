package com.restaurant.restaurant_management.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_management.dto.Messagesdto;
import com.restaurant.restaurant_management.model.Messages;
import com.restaurant.restaurant_management.service.MessageService;
//code by vidya
@RestController
@RequestMapping("/messages")
public class MessageController {
private MessageService messageService;
@Autowired
public MessageController(MessageService messageService) {
	this.messageService = messageService;
}

//comment by vidya 
@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
@GetMapping("/all")
public List<Messagesdto> allEmployees() {
	return messageService.getAllMessages();
}

@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER', 'ADMIN')")
@PostMapping("/add")
public Messages addMessage(@RequestBody Messages message) throws ParseException {	
	messageService.save(message);
	return message;
}
@PreAuthorize("hasAnyRole('EMPLOYEE','MANAGER','ADMIN')")
	@GetMapping("/{messageId}")
	public Messages employeeById(@PathVariable("messageId") long messageId) {

		return messageService.findById(messageId);
	}

@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{messageId}")
public void deleteEmployee(@PathVariable("messageId") long messageId) {
	messageService.deleteMeassageById(messageId);
}
}