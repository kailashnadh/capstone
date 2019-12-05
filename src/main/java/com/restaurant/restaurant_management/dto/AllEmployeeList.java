package com.restaurant.restaurant_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AllEmployeeList {
private long emp_id;
	
	private String firstname;
	
	private String lastname;

}
