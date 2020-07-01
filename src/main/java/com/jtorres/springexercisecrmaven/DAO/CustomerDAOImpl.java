package com.jtorres.springexercisecrmaven.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtorres.springexercisecrmaven.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Customer> getCustomers() {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
		
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		// if id=0, save. else, update
		currentSession.saveOrUpdate(theCustomer);
		
	}
	
	@Override
	public void deleteCustomer(Customer theCustomer) {
		Session currentSession = entityManager.unwrap(Session.class);
		System.out.println( theCustomer.getId());
		Query query = currentSession.createQuery("delete from Customer where id = " + theCustomer.getId());
        query.executeUpdate();
        
	}

}
