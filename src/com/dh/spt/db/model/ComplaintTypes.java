package com.dh.spt.db.model;

// Generated 22 Nov, 2014 6:09:24 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;

/**
 * ComplaintTypes generated by hbm2java
 */
public class ComplaintTypes implements java.io.Serializable {

	private String complaintTypeCode;
	private String complaintTypeName;
	private Set<Complaints> complaintses = new HashSet<Complaints>(0);

	public ComplaintTypes() {
	}

	public ComplaintTypes(String complaintTypeCode) {
		this.complaintTypeCode = complaintTypeCode;
	}

	public ComplaintTypes(String complaintTypeCode, String complaintTypeName,
			Set<Complaints> complaintses) {
		this.complaintTypeCode = complaintTypeCode;
		this.complaintTypeName = complaintTypeName;
		this.complaintses = complaintses;
	}

	public String getComplaintTypeCode() {
		return this.complaintTypeCode;
	}

	public void setComplaintTypeCode(String complaintTypeCode) {
		this.complaintTypeCode = complaintTypeCode;
	}

	public String getComplaintTypeName() {
		return this.complaintTypeName;
	}

	public void setComplaintTypeName(String complaintTypeName) {
		this.complaintTypeName = complaintTypeName;
	}

	public Set<Complaints> getComplaintses() {
		return this.complaintses;
	}

	public void setComplaintses(Set<Complaints> complaintses) {
		this.complaintses = complaintses;
	}

}
