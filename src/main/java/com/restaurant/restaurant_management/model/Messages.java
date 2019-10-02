package com.restaurant.restaurant_management.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name="messages")
@Entity
public class Messages {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long message_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="emp_id")
	private Employee employee;
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "message_time")
	private Date message_time;
	@Column
	private String message_text;
}
