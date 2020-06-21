package com.jtorres.springexercisecrmaven.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jtorres.springexercisecrmaven.entity.Rulesheet;


@Repository
public class RulesheetDAOImpl implements RulesheetDAO {

	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Rulesheet> getRulesheets() {
		
		Session currentSession = entityManager.unwrap(Session.class);
				
		try {
			
			
			Query<Rulesheet> query = currentSession.createQuery("from Rulesheet", Rulesheet.class);
			
			List<Rulesheet> rulesheets = query.getResultList();
			
			return rulesheets;

		} catch (Exception e) {
			System.out.println("[RulesheetDAOImpl] Error in getRulesheets()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveRulesheet(@Valid Rulesheet therulesheet) {
		Session currentSession = entityManager.unwrap(Session.class);
		
		// if id=0, save. else, update
		currentSession.saveOrUpdate(therulesheet);
		
	}

}
