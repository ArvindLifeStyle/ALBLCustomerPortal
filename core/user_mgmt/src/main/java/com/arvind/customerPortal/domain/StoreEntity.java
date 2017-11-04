package com.arvind.customerPortal.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "albl_store")
public class StoreEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "store_id")
	private String storeid;

	@Column(name = "store_name")
	private String name = null;

	@Column(name = "store_address")
	private String address = null;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_detail_id")	
	private PhoneEntity phone;

	public String getStoreid() {
		return storeid;
	}

	public void setStoreid(String storeid) {
		this.storeid = storeid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public PhoneEntity getPhone() {
		return phone;
	}

	public void setPhone(PhoneEntity phone) {
		this.phone = phone;
	}
	
}
