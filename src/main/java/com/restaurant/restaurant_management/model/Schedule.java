package com.restaurant.restaurant_management.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="schedule")
@Entity
public class Schedule {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long schedule_id;
	
	
	private long emp_id;
	
    private String schedule_start;

    private String schedule_end;
	
}
