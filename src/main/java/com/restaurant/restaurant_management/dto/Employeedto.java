package com.restaurant.restaurant_management.dto;

import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.restaurant.restaurant_management.model.Address;
import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Roles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employeedto {

private long emp_id;
	
	private String firstname;
	
	private String lastname;
	
	private String gender;
	
	private Date date;
	

	private String email;
	
	private long phonenumber;
	
	private byte[] photo;
	
	private Address address_id;
	
	
	
}
