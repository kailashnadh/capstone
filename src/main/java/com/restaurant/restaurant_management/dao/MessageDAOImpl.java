package com.restaurant.restaurant_management.dao;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.dto.Messagesdto;
import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Messages;

@Repository
public class MessageDAOImpl implements MessageDAO {
	private EntityManager entityManager;
	@Autowired
	public MessageDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}
	
	public List<Messagesdto> getMessages() {
		Query theQuery =entityManager.createNativeQuery("SELECT m.id,m.message_text,m.message_time, CONCAT(e.firstname,' ',e.lastname) as employee_name FROM messages m join employee e on m.employee_id=e.emp_id");
		List<Messagesdto> messages = new ArrayList();	
		for (Object o : theQuery.getResultList()) {
		    Object[] cols = (Object[]) o;
		    Messagesdto tmpG = new Messagesdto();
		    tmpG.setMessage_id(((BigInteger) cols[0]).longValue());
		    tmpG.setMessage_text(cols[1].toString());
		    tmpG.setMessage_time(cols[2].toString());
		    tmpG.setEmployee_name(cols[3].toString());
		    messages.add(tmpG);
		}
		System.out.println("in Messages DAO"+messages.get(0).getEmployee_name());
		return messages;
	}

	@Override
	public Messages findById(Long id) {
		Messages message = 
				entityManager.find(Messages.class, id);
		return message;
	}

	@Override
	public void save(Messages message) {
		Messages dbmessagee = entityManager.merge(message);
		message.setMessage_id(dbmessagee.getMessage_id());

	}

	@Override
	public void deleteMeassageById(Long id) {
		Query theQuery = entityManager.createQuery(
				"delete from Messages where id=:messageId");

theQuery.setParameter("messageId", id);

theQuery.executeUpdate();

	}

}