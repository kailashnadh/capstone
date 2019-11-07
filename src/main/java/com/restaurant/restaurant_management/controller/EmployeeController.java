package com.restaurant.restaurant_management.controller;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.restaurant_management.dto.EmpImage;
import com.restaurant.restaurant_management.dto.Employeedto;
import com.restaurant.restaurant_management.dto.Password;
import com.restaurant.restaurant_management.dto.setRole;
import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.service.EmployeeService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
private EmployeeService employeeService;
@Autowired
private BCryptPasswordEncoder bcryptEncoder;
	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
//Added by Surendher
	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) {	
		employee.setPassword(bcryptEncoder.encode(employee.getPassword()));
		employeeService.save(employee);
		return employee;
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public Iterable<Employee> allEmployees() {
    	List<Employee> e=employeeService.findAll();
    	return e;
	}

    //@Secured("ROLE_USER")
    //@PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{employeeId}")
	public Employee employeeById(@PathVariable("employeeId") long employeeId) {

		return employeeService.findById(employeeId);
	}
    
    @PreAuthorize("hasAnyRole('USER', 'MANAGER','ADMIN')")
	@GetMapping("/getbyemail/{employeeName}")
	public Employee findByEmailId(@PathVariable("employeeName") String employeeEmail) {

		return employeeService.findByEmailId(employeeEmail);
	}
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
 	@GetMapping("/manager/{employeeId}")
 	public Employee getManagerbyEmployeeId(@PathVariable("employeeId") long employeeId) {

 		return employeeService.getMangerFromEmployeeId(employeeId);
 	}
    @PreAuthorize("hasAnyRole('USER','MANAGER', 'ADMIN')")
	@PutMapping("/update")
	public Employeedto updateEmployee(@RequestParam(value = "myFile",required = false) MultipartFile file,
            @RequestParam(value="employee",required = false)String employee) throws IOException{
    	try {
    		System.out.println("in update controller"+employee);
        	EmpImage jsonAd = new ObjectMapper().readValue(employee, EmpImage.class);
        	Employeedto empDto=new Employeedto(jsonAd.getEmp_id(),
        										jsonAd.getFirstname(),
        										jsonAd.getLastname(),
        										jsonAd.getGender(),
        										jsonAd.getDate(),
        										jsonAd.getEmail(),
        										jsonAd.getPhonenumber(),
        										file.getBytes(),
        										jsonAd.getAddress_id());
//        	jsonAd.setPhoto(file.getBytes());
    		 employeeService.updateEmployee(empDto);
    		 return empDto;
    	}
    	catch(Exception e) {
    		EmpImage jsonAd = new ObjectMapper().readValue(employee, EmpImage.class);
        	Employeedto empDto=new Employeedto();
        	empDto.setEmp_id(jsonAd.getEmp_id());
        	empDto.setFirstname(jsonAd.getFirstname());
        	empDto.setLastname(jsonAd.getLastname());
        	empDto.setGender(jsonAd.getGender());
        	empDto.setDate(jsonAd.getDate());
        	empDto.setEmail(jsonAd.getEmail());
        	empDto.setPhonenumber(jsonAd.getPhonenumber());
        	empDto.setAddress_id(jsonAd.getAddress_id());
        	employeeService.updateEmployee(empDto);
        	return empDto;
    		
    	}
    	
    	
	}
    
//    @PutMapping("/upload")
//	public Employeedto uploadImage(@RequestParam("myFile") MultipartFile file) throws IOException {
//    	System.out.println("in update controller"+employee);
//		 employeeService.updateEmployee(employee);
//		 return employee;
//	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{messageId}")
	public void deleteCountry(@PathVariable("messageId") long employeeId) {
		employeeService.deleteById(employeeId);
	}
	
	@PreAuthorize("hasAnyRole('USER', 'MANAGER','ADMIN')")
	@PutMapping("/changePassword")
	public boolean updatePassword(@RequestBody Password newPassword) {
		Employee currentEmployee=employeeService.findById(newPassword.getEmp_id());
		boolean result = bcryptEncoder.matches(newPassword.getCurrentPassword(), currentEmployee.getPassword());
		if(result) {
			newPassword.setNewPassword(bcryptEncoder.encode(newPassword.getNewPassword()));
			employeeService.updatePassword(newPassword);
			return result;
		}
		
		else {
			return false;
		}
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/setRole")
	public void setRolebyId(@RequestBody setRole values) {
		System.out.println("in controller setroles"+values);
		 employeeService.setRolebyId(values.getEmp_id(), values.getRole_id());
	}
}
