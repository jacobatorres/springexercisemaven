package com.jtorres.springexercisecrmaven.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.jtorres.springexercisecrmaven.validation.DoesCustomerExist;


@Entity
@Table(name="rulesheet")
public class Rulesheet {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	
	@Size(min=1, message="type name not be blank")
	@Pattern(regexp="^[a-zA-z0-9]+_[0-9]+", message = "must look like ruleA_352")
	@DoesCustomerExist(message = "customer does not exist")
	@Transient
	private String filename;
	
	@Column(name="type")
	private String type;

	

	@Column(name = "customer_id")
	private int customerId;
	

	
	@Size(min=1, message="file content not be blank")
	@Column(name = "filecontent")
	private String filecontent;
	


	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;
	
	public  Rulesheet() {
		
	}


	public int getcustomerId() {
		return customerId;
	}


	public void setcustomerId(int customerId) {
		this.customerId = customerId;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFilecontent() {
		return filecontent;
	}

	public void setFilecontent(String filecontent) {
		this.filecontent = filecontent;
	}


	@Override
	public String toString() {
		return "Rulesheet [id=" + id + ", filename=" + filename + ", type=" + type + ", customerId=" + customerId
				+ ", filecontent=" + filecontent + ", createDate=" + createDate + "]";
	}


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
	
}
