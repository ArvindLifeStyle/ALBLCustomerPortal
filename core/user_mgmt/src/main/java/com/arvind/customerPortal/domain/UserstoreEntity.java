package com.arvind.customerPortal.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="albl_userstore")
public class UserstoreEntity {
	
	@Id
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "store_id")
	private String storeId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_dtime")
	private Date createdDate;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
