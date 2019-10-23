package com.restaurant.restaurant_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Messages;
import com.restaurant.restaurant_management.model.Schedule;
import com.restaurant.restaurant_management.service.MessageService;
import com.restaurant.restaurant_management.service.ScheduleService;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {


private ScheduleService scheduleService;
@Autowired
public ScheduleController(ScheduleService scheduleService) {
	this.scheduleService = scheduleService;
}

@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
@GetMapping("/all")
public Iterable<Schedule> getSchedules() {
	return scheduleService.getSchedules();
}

@PreAuthorize("hasAnyRole('EMPLOYEE', 'ADMIN')")
@PostMapping("/add")
public Schedule addMessage(@RequestBody Schedule schedule) {
	System.out.println("In schedule controller"+schedule);
	scheduleService.save(schedule);
	return schedule;
}
@PutMapping("/update")
public Schedule updateScchedule(@RequestBody Schedule schedule) {
	scheduleService.save(schedule);
	 return schedule;
}
	
}
