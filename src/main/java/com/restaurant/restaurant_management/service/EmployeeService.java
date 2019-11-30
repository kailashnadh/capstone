package com.restaurant.restaurant_management.service;

import java.util.List;

import com.restaurant.restaurant_management.dto.Employeedto;
import com.restaurant.restaurant_management.dto.Password;
import com.restaurant.restaurant_management.model.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public List<Employee> allManagers();
	public Employee findByEmailId(String emailId);
	
	public Employee findById(Long id);
	
	public void save(Employee theEmployee);
	public void updateEmployee(Employeedto employee);
	
	public void deleteById(Long id);
	
	public void setRolebyId(Long emp_id,Long role_id);
	public Employee getMangerFromEmployeeId(Long id);
	public void updatePassword(Password newPassword);
	public boolean isEmployeeExists(String email);
}
