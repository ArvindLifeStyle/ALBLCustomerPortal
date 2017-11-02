package com.arvind.customerPortal.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the users_role database table.
 * 
 */
@Entity
@Table(name="users_role")
@NamedQuery(name="UsersRole.findAll", query="SELECT u FROM UsersRole u")
public class UsersRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="created_by")
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_dtime")
	private Date createdDtime;

	@Column(name="last_modified_by")
	private String lastModifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_modified_dtime")
	private Date lastModifiedDtime;

	@Column(name="role_id")
	private int roleId;

	@Column(name="user_id")
	private int userId;

	public UsersRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDtime() {
		return this.createdDtime;
	}

	public void setCreatedDtime(Date createdDtime) {
		this.createdDtime = createdDtime;
	}

	public String getLastModifiedBy() {
		return this.lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDtime() {
		return this.lastModifiedDtime;
	}

	public void setLastModifiedDtime(Date lastModifiedDtime) {
		this.lastModifiedDtime = lastModifiedDtime;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}