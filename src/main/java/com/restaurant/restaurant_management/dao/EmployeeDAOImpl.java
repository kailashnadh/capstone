package com.restaurant.restaurant_management.dao;

import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.restaurant.restaurant_management.dto.Employeedto;
import com.restaurant.restaurant_management.dto.Password;
import com.restaurant.restaurant_management.model.Employee;
import com.restaurant.restaurant_management.model.Roles;
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// create a query
				Query theQuery = 
						entityManager.createQuery("from Employee");
			
				
				// execute query and get result list
				List<Employee> employees = theQuery.getResultList();
				
					
				//String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
				
				// return the results		
				return employees;
	}
	
	@Override
	public List<Employee> allManagers() {
		Query theQuery =entityManager.createNativeQuery("SELECT * FROM employee join employee_roles on employee.emp_id =employee_roles.emp_id where "
				+ "employee_roles.role_id=2");
		List<Employee> managers = theQuery.getResultList();	
		return managers;
	}

	@Override
	public Employee findById(Long id) {
		// get employee
		Employee theEmployee = 
				entityManager.find(Employee.class, id);
		
		// return employee
		return theEmployee;
	}

	@Override
	public void save(Employee theEmployee) {
		// save or update the employee
				Employee dbEmployee = entityManager.merge(theEmployee);
				
				// update with id from db ... so we can get generated id for save/insert
				theEmployee.setEmp_id(dbEmployee.getEmp_id());

	}

	@Override
	public void deleteById(Long id) {
		// delete object with primary key
		Query theQuery = entityManager.createQuery(
							"delete from Employee where id=:employeeId");
		
		theQuery.setParameter("employeeId", id);
		
		theQuery.executeUpdate();
	}

	@Override
	public Employee findByEmailId(String emailId) {
		// get employee
				//Employee theEmployee = 
						//entityManager.find(Employee.class, emailId);
				Query theQuery = entityManager.createQuery(
						"select e from Employee e where email=:emailId");
				theQuery.setParameter("emailId", emailId);
				Employee theEmployee =(Employee)theQuery.getSingleResult();
				// return employee
				return theEmployee;
	}
	@Override
	public Set<Roles> getRoles(Long emp_id) {
		/*Query theQuery = entityManager.createQuery(
				"select r from Roles r JOIN Employee_roles er "
				+ "with er.emp_id=:emp_id");*/
		Query theQuery =entityManager.createNativeQuery("select * from Roles r join Employee_roles er"
				+ "on r.id=er.role_id where er.emp_id=:emp_id");
		theQuery.setParameter("emp_id", emp_id);
		Set<Roles> roles = new HashSet();
			List<Roles> rolesList=theQuery.getResultList();
			for(Roles r :rolesList) {
				if( !roles.contains(r)) {
					roles.add(r);
				}
			}	
		return roles;
	}
	
	
	
	@Override
	public Employee getMangerFromEmployeeId(Long id) {
		Query theQuery = entityManager.createQuery(
				"select m from Employee e join e.manager m where  e.emp_id=:empID");
		theQuery.setParameter("empID", id);
		Employee theEmployee =(Employee)theQuery.getSingleResult();
		return theEmployee;
	}

	@Override
	public void setRolebyId(Long emp_id, Long role_id) {
		System.out.print("employeeid in setrole"+emp_id);
		System.out.print("roleid in setrole"+role_id);
		// TODO Auto-generated method stub
		Query theQuery=entityManager.createNativeQuery(
				"UPDATE employee_roles SET role_id = ? WHERE employee_roles.emp_id = ?");
		theQuery.setParameter(1, role_id);
		theQuery.setParameter(2, emp_id);
		theQuery.executeUpdate();
		System.out.println(theQuery);
	}

	@Override
	public void updateEmployee(Employeedto employee) {
		if(employee.getPhoto()!=null) {
			Query theQuery=entityManager.createNativeQuery("UPDATE employee, address\r\n" + 
					"		SET employee.email = ?, employee.firstname = ?,employee.lastname = ?,employee.phonenumber=?,employee.photo=?,address.city=?,address.pincode=?,address.street=?\r\n" + 
					"		WHERE employee.emp_id = ? AND address.id=?");
			
			
			theQuery.setParameter(1, employee.getEmail());
			theQuery.setParameter(2, employee.getFirstname());
			theQuery.setParameter(3, employee.getLastname());
			theQuery.setParameter(4, employee.getPhonenumber());
			theQuery.setParameter(5, employee.getPhoto());
			theQuery.setParameter(6, employee.getAddress_id().getCity());
			theQuery.setParameter(7, employee.getAddress_id().getPincode());
			theQuery.setParameter(8, employee.getAddress_id().getStreet());
			theQuery.setParameter(9, employee.getEmp_id());
			theQuery.setParameter(10, employee.getAddress_id());
			theQuery.executeUpdate();	
		}
		
		else {
			Query theQuery=entityManager.createNativeQuery("UPDATE employee, address\r\n" + 
					"		SET employee.email = ?, employee.firstname = ?,employee.lastname = ?,employee.phonenumber=?,address.city=?,address.pincode=?,address.street=?\r\n" + 
					"		WHERE employee.emp_id = ? AND address.id=?");
			
			
			theQuery.setParameter(1, employee.getEmail());
			theQuery.setParameter(2, employee.getFirstname());
			theQuery.setParameter(3, employee.getLastname());
			theQuery.setParameter(4, employee.getPhonenumber());
			theQuery.setParameter(5, employee.getAddress_id().getCity());
			theQuery.setParameter(6, employee.getAddress_id().getPincode());
			theQuery.setParameter(7, employee.getAddress_id().getStreet());
			theQuery.setParameter(8, employee.getEmp_id());
			theQuery.setParameter(9, employee.getAddress_id());
			theQuery.executeUpdate();
		}
		
			
	}

	@Override
	public void updatePassword(Password newPassword) {
		Query theQuery=entityManager.createNativeQuery(
				"UPDATE employee SET emp_password = ? WHERE employee.emp_id = ?");
		theQuery.setParameter(1, newPassword.getNewPassword());
		theQuery.setParameter(2, newPassword.getEmp_id());
		theQuery.executeUpdate();
		
	}

	@Override
	public boolean isEmployeeExists(String email) {
		// TODO Auto-generated method stub
		Query theQuery = entityManager.createQuery(
				"select count(*) from Employee e where email=:emailId");
		theQuery.setParameter("emailId", email);
		System.out.println("email is "+email);
		Long count= (Long)theQuery.getSingleResult();
		
		if(count!= null &&count.intValue()!=0) {
			return true;
		}else {
			return false;
		}
		
		//Employee theEmployee =(Employee)
		
	}
}
