package com.jtorres.springexercisecrmaven.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="customer")
public class Customer {
	
	// set varialbes
	// annotate them
	// get set
	// constructors
	// tostring
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;


	@OneToMany(fetch=FetchType.LAZY,  cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private List<Rulesheet> rulesheets;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<Rulesheet> getRulesheets() {
		return rulesheets;
	}

	public void setRulesheets(List<Rulesheet> rulesheets) {
		this.rulesheets = rulesheets;
	}

	public Customer() {
		
	}

	public Customer(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", createDate=" + createDate + ", rulesheets=" + rulesheets
				+ "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public void addRulesheet(Rulesheet rulesheet) {
		if(rulesheets == null) {
			rulesheets = new ArrayList<>();
		}
		
		rulesheets.add(rulesheet);
	}
	



}
