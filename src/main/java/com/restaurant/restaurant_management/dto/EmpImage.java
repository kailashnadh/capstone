package com.restaurant.restaurant_management.dto;

import java.sql.Date;

import com.restaurant.restaurant_management.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpImage {

private long emp_id;
	
	private String firstname;
	
	private String lastname;
	
	private String gender;
	
	private Date date;
	
	private String photo;
	private String email;
	private String roles;
	
	private int phonenumber;
	
	private Address address_id;
}
