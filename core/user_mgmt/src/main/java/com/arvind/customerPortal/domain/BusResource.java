package com.arvind.customerPortal.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the bus_resource database table.
 * 
 */
@Entity
@Table(name="bus_resource")
@NamedQuery(name="BusResource.findAll", query="SELECT b FROM BusResource b")
public class BusResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="resource_id")
	private int resourceId;

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

	private String resource;

	public BusResource() {
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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

	public String getResource() {
		return this.resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

}