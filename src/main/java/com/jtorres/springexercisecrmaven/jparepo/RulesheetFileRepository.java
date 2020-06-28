package com.jtorres.springexercisecrmaven.jparepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jtorres.springexercisecrmaven.entity.Rulesheet;

@Repository
public interface RulesheetFileRepository extends JpaRepository<Rulesheet, String> {

}
