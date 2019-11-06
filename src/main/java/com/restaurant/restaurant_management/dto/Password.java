package com.restaurant.restaurant_management.dto;

import lombok.Data;

@Data
public class Password {
	private String currentPassword;
	private String newPassword;
	private Long emp_id;
}
