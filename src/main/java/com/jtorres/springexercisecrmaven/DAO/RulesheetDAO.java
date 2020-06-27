package com.jtorres.springexercisecrmaven.DAO;

import java.util.List;

import javax.validation.Valid;

import com.jtorres.springexercisecrmaven.entity.Rulesheet;


public interface RulesheetDAO {
	
	public List<Rulesheet> getRulesheets();

	public void saveRulesheet(Rulesheet therulesheet);

}
