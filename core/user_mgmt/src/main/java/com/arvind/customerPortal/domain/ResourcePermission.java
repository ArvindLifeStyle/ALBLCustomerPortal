package com.arvind.customerPortal.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the resource_permission database table.
 * 
 */
@Entity
@Table(name="resource_permission")
@NamedQuery(name="ResourcePermission.findAll", query="SELECT r FROM ResourcePermission r")
public class ResourcePermission implements Serializable {
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

	private String permission;

	@Column(name="resource_id")
	private int resourceId;

	public ResourcePermission() {
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

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

}