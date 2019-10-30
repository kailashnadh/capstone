package com.restaurant.restaurant_management.dto;

import java.sql.Date;
import java.util.List;

import com.restaurant.restaurant_management.model.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpList {

	private List<EmpImage> emplist;
}
