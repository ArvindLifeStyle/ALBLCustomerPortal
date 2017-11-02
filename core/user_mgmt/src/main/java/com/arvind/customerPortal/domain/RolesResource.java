package com.arvind.customerPortal.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the roles_resources database table.
 * 
 */
@Entity
@Table(name="roles_resources")
@NamedQuery(name="RolesResource.findAll", query="SELECT r FROM RolesResource r")
public class RolesResource implements Serializable {
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

	@Column(name="resource_id")
	private int resourceId;

	@Column(name="role_id")
	private int roleId;

	public RolesResource() {
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

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}