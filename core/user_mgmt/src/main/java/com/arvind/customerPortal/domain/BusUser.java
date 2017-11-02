package com.arvind.customerPortal.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bus_user database table.
 * 
 */
@Entity
@Table(name="bus_user")
@NamedQuery(name="BusUser.findAll", query="SELECT b FROM BusUser b")
public class BusUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;

	private String active;

	@Column(name="bus_usercol")
	private String busUsercol;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dtime")
	private Date createdDtime;

	@Column(name="created_user")
	private String createdUser;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_dtime")
	private Date lastModifiedDtime;

	@Column(name="last_modified_user")
	private String lastModifiedUser;

	private String name;

	private String nick;

	private String password;

	private String verified;

	public BusUser() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getBusUsercol() {
		return this.busUsercol;
	}

	public void setBusUsercol(String busUsercol) {
		this.busUsercol = busUsercol;
	}

	public Date getCreatedDtime() {
		return this.createdDtime;
	}

	public void setCreatedDtime(Date createdDtime) {
		this.createdDtime = createdDtime;
	}

	public String getCreatedUser() {
		return this.createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastModifiedDtime() {
		return this.lastModifiedDtime;
	}

	public void setLastModifiedDtime(Date lastModifiedDtime) {
		this.lastModifiedDtime = lastModifiedDtime;
	}

	public String getLastModifiedUser() {
		return this.lastModifiedUser;
	}

	public void setLastModifiedUser(String lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return this.nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVerified() {
		return this.verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

}