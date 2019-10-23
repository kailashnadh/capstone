package com.restaurant.restaurant_management.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Schedule;
@Repository
public class ScheduleDAOImpl implements ScheduleDAO {
	private EntityManager entityManager;
	@Autowired
	public ScheduleDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	@Override
	public List<Schedule> getSchedules() {
		// create a query
				Query theQuery = 
						entityManager.createQuery("from Schedule");
			
				
				// execute query and get result list
				List<Schedule> schedule = theQuery.getResultList();
				
					
				//String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
				
				// return the results		
				return schedule;
	}

	@Override
	public List<Schedule> getAllSchedulesForDate(Date ScheduleDay) {
		Query theQuery = 
				entityManager.createQuery("from Schedule where schedule_id=:date");
		theQuery.setParameter("date", ScheduleDay);
		List<Schedule> schedules = theQuery.getResultList();	
		return schedules;
	}

	@Override
	public void save(Schedule schedule) {
		System.out.println("In schedule daoimplement"+schedule);
		Schedule dbSchedule = entityManager.merge(schedule);
		schedule.setSchedule_id(dbSchedule.getSchedule_id()); 
	}

}